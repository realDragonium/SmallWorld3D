package View;

import Controller.*;
import Objects.Xform;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Map3DView {

    private Group root;
    private Xform xForm = new Xform();
    private MapController mapCon;

    private Group world = new Group();
    private SubScene scene;
    private Group map = new Group();
    private Group camera = new Group();
    private Group fiches = new Group();
    private GameController gameCon;
    private Group table = new Group();

    public Map3DView(MapController mapCon, Group map) {
        System.out.println("creating the map");
        scene = new SubScene(world, 1920, 1000, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.rgb(116, 144, 153));
        this.mapCon = mapCon;
        this.gameCon = mapCon.getGameCon();
        map.getChildren().add(scene);
        System.out.println("setting the 3d group");
        mapCon.getGameCon().set3dGroup(fiches);
        System.out.println("loading the map");
        loadMap();
        createCamera();
        createTable();
        world.getChildren().addAll(fiches);
    }

    public void loadMap(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/3dObjects/map.fxml"));
            Group map = fxmlLoader.load();
            int numberOfElements = map.getChildren().size()-1;
            for(int i = 0; i < numberOfElements; i++){
                Node area = map.getChildren().get(i);
                if(!area.getId().equals("nope")) {
                    String areaId = area.getId().substring(14);
                    area.setId(areaId);
                    mapCon.createAreaView(area, map);
                }
            }
            map.setScaleX(100);
            map.setScaleY(100);
            map.setScaleZ(100);
            xForm.getChildren().add(map);
//            xForm.setRotateX(180);
            world.getChildren().add(xForm);

            // ...
        }
        catch (IOException e) {
            // exception handling
        }
    }

    public void setCamera(PerspectiveCamera camera){
        scene.setCamera(camera);
    }

    public void createCamera(){
        System.out.println("creating camera...");
        CameraView cameraView = gameCon.createCamera(camera);
        world.getChildren().add(camera);
        setCamera(cameraView.getCamera());
    }

    public void createTable(){
        new TableView(table);
        world.getChildren().add(table);
    }


//    public FicheController createRaceFiche(String race){
//        FicheController ficheCon = new FicheController(1, race);
//        new fiche3dView(ficheCon, fiches, race);
//        return ficheCon;
//    }

}
