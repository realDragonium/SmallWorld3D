package Controller;

import View.CameraView;
import View.Map3DView;
import View.TableView;
import View.fiche3dView;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class Controller3D{

    Group world = new Group();
    SubScene scene;
    Group map = new Group();
    Group camera = new Group();
    Group fiches = new Group();
    Group table = new Group();
    GameController gameCon;

    public Controller3D(GameController gameCon, Group group){
        this.gameCon = gameCon;
        scene = new SubScene(world, 1920, 1080, true, SceneAntialiasing.BALANCED);
        createMap();
        createCamera();
        createTable();

        group.getChildren().add(scene);
        scene.setFill(Color.rgb(116, 144, 153));
        world.getChildren().add(fiches);
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
        MapController mapCon = gameCon.getMapCon();
        new Map3DView(mapCon, map);
        world.getChildren().add(map);
    }

    public void createTable(){
        new TableView(table);
        world.getChildren().add(table);
    }

//    public FicheController createRaceFiche(String race){
//        FicheController ficheCon = new FicheController(1, "Ghost");
//        new fiche3dView(ficheCon, fiches, race);
//        return ficheCon;
//    }

    //deze staat nu in area3DView
    public void add3dObject(Node number) {
        map.getChildren().add(number);
    }
}
