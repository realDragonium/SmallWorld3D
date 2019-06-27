package Controller;

import View.CameraView;
import View.Map3DView;
import View.fiche3dView;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class Controller3D{

    Group world = new Group();
    SubScene scene;
    Group map = new Group();
    Group camera = new Group();
    Group fiches = new Group();
    GameController gameCon;

    public Controller3D(GameController gameCon, Group group){
        this.gameCon = gameCon;
        scene = new SubScene(world, 1600, 900, true, SceneAntialiasing.BALANCED);
        createMap();
        createCamera();
        group.getChildren().add(scene);
        world.getChildren().add(fiches);
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

    public FicheController createRaceFiche(String race){
        FicheController ficheCon = new FicheController(gameCon, 1);
        new fiche3dView(ficheCon, fiches, race);
        return ficheCon;
    }

    public void add3dObject(Node number) {
        map.getChildren().add(number);
    }
}
