package View;

import Controller.*;
import Objects.NormalFXMLLoader;
import Objects.Xform;
import javafx.scene.*;
import javafx.scene.paint.Color;
import Enum.View3DEnum;

public class Map3DView {

    private Group root;
    private Xform xForm = new Xform();
    private MapController mapCon;

    private Group world = new Group();
    private SubScene scene;

    public Map3DView(MapController mapCon, Group map) {
        System.out.println("creating the map");
        scene = new SubScene(world, 1920, 1000, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.rgb(116, 144, 153));
        this.mapCon = mapCon;
        map.getChildren().add(scene);
        System.out.println("loading the map");
        loadMap();
        createCamera();
        createTable();
        loadGroups();
    }

    public void loadMap(){
        Group map = new NormalFXMLLoader("/3dObjects/map.fxml").loadGroup();
        Group numbers = new Group();
        int numberOfElements = map.getChildren().size();
        for(int i = 0; i < numberOfElements; i++){
            Node area = map.getChildren().get(i);
            if(!area.getId().equals("nope")) {
                String areaId = area.getId().substring(14);
                area.setId(areaId);
                mapCon.createAreaView(area, numbers);
            }
        }
        map.setScaleX(100);
        map.setScaleY(100);
        map.setScaleZ(100);
        xForm.getChildren().add(map);
//            xForm.setRotateX(180);
        world.getChildren().add(xForm);
        world.getChildren().add(numbers);
    }

    private void setCamera(PerspectiveCamera camera){
        scene.setCamera(camera);
    }

    private void createCamera(){
        System.out.println("creating camera...");
        CameraView cameraView = mapCon.createCamera();
        setCamera(cameraView.getCamera());
    }

    private void createTable(){
        new TableView();
    }

    private void loadGroups(){
        world.getChildren().add(View3DEnum.CAMERA.getGroup());
        world.getChildren().add(View3DEnum.FICHES.getGroup());
        world.getChildren().add(View3DEnum.TABLE.getGroup());
        world.getChildren().add(View3DEnum.CRYSTAL.getGroup());
        world.getChildren().add(View3DEnum.SPECIALPROP.getGroup());
    }


}
