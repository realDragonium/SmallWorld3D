package View;

import Controller.*;
import Objects.Xform;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import java.io.IOException;

public class Map3DView {

    private Group root;
    private Xform xForm = new Xform();
    private MapController mapCon;

    Group world = new Group();
    SubScene scene;
    Group map = new Group();
    Group camera = new Group();
    Group fiches = new Group();
    GameController gameCon;

    public Map3DView(MapController mapCon, Group map) {
        root = map;
        this.mapCon = mapCon;
        loadMap();

        scene = new SubScene(world, 1600, 900, true, SceneAntialiasing.BALANCED);

        createCamera();
        root.getChildren().add(scene);
        world.getChildren().addAll(fiches);
    }

    public void loadMap(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/3dObjects/map.fxml"));
            Group map = fxmlLoader.load();
            for(Node area : map.getChildren()){
                if(!area.getId().equals("nope")) {
                    String areaId = area.getId().substring(14);
                    area.setId(areaId);
                    mapCon.createAreaView(area, areaId);

                }
            }
            map.setScaleX(100);
            map.setScaleY(100);
            map.setScaleZ(100);
            xForm.getChildren().add(map);
//            xForm.setRotateX(180);
            root.getChildren().add(xForm);

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
        CameraController cameraCon = new CameraController();
        CameraView cameraView = new CameraView(cameraCon, camera);
        world.getChildren().add(camera);
        setCamera(cameraView.getCamera());
    }

    public void createMap(){
        System.out.println("creating map...");
        new Map3DView(mapCon, map);
        world.getChildren().add(map);
    }

    public FicheController createRaceFiche(String race){
        FicheController ficheCon = new FicheController(1, "Ghost");
        new fiche3dView(ficheCon, fiches, race);
        return ficheCon;
    }

    public void add3dObject(Node number) {
        map.getChildren().add(number);
    }
}
