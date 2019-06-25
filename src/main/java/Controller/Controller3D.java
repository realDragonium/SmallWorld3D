package Controller;

import View.CameraView;
import View.Map3DView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller3D{

    Group world;
    Scene scene;
    Group map = new Group();
    Group camera = new Group();

    public Controller3D(Stage primaryStage){
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createMap();
        createCamera();

        System.out.println(world.getChildren());
    }

    public void setCamera(PerspectiveCamera camera){
        scene.setCamera(camera);
    }

    public void createCamera(){
        CameraController cameraCon = new CameraController(this);
        CameraView cameraView = new CameraView(cameraCon, camera);
        world.getChildren().add(camera);
        setCamera(cameraView.getCamera());
    }

    public void createMap(){
        Map3DController mapCon = new Map3DController(this);
        new Map3DView(mapCon, map);
        world.getChildren().add(map);
    }

    public void start(Stage primaryStage) throws Exception {
        world = new Group();
        scene = new Scene(world, 1024, 768, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Small World");
        primaryStage.show();
    }
}
