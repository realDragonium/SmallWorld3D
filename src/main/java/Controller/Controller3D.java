package Controller;

import View.CameraView;
import View.Map3DView;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class Controller3D{

    Group world = new Group();
    SubScene scene;
    Group map = new Group();
    Group camera = new Group();
    GameController gameCon;

    public Controller3D(GameController gameCon, Group group){
        this.gameCon = gameCon;
        scene = new SubScene(world, 1600, 900, true, SceneAntialiasing.BALANCED);
        createMap();
        createCamera();
        group.getChildren().add(scene);
    }

    public void setCamera(PerspectiveCamera camera){
        scene.setCamera(camera);
    }

    public void createCamera(){
        System.out.println("creating camera...");
        CameraController cameraCon = new CameraController(this);
        CameraView cameraView = new CameraView(cameraCon, camera);
        world.getChildren().add(camera);
        setCamera(cameraView.getCamera());
    }

    public void createMap(){
        System.out.println("creating map...");
        Map3DController mapCon = new Map3DController(this, gameCon);
        new Map3DView(mapCon, map);
        world.getChildren().add(map);
    }
}
