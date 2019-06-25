package View;

import Controller.ApplicationController;
import Enum.ApplicatieViewEnum;
import Observable.ApplicatieObservable;
import Observer.ApplicatieObserver;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ApplicationView implements ApplicatieObserver {
    private Scene scene;
    private Stage primaryStage;
    private Group root = new Group();
    private Map<String, Group> groups = new HashMap<>();

    private ApplicationController appCon = new ApplicationController();

    public ApplicationView(Stage primaryStage){
        this.primaryStage = primaryStage;
        root.getChildren().add(new Button("ApplicationView!"));

        createViewGroups();
        createViews();

        appCon.register(this);
        setStartScreen();

        startPrimaryStage();
    }

    private void setStartScreen(){
        appCon.setActiveView(ApplicatieViewEnum.GAME);
    }

    private void createViews(){
        appCon.createLoginController(groups.get("login"));
        appCon.createHomeScreenController(groups.get("homescreen"));
        appCon.createGameController(groups.get("game"));
    }

    private void createViewGroups(){
        groups.put("login", new Group());
        groups.put("game", new Group());
        groups.put("homescreen", new Group());
    }

    private void startPrimaryStage(){
        scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setActive(ApplicatieViewEnum view) {
        root.getChildren().clear();
        root.getChildren().add(groups.get(view.getStringValue()));
    }

    private void addSubScene(SubScene subScene){
        if(!root.getChildren().contains(subScene)) {
            System.out.println("adding 3d");
            root.getChildren().add(subScene);
        }
    }

    @Override
    public void update(ApplicatieObservable ao) {
        setActive(ao.getCurrentView());
        if(ao.get3dScene() != null) addSubScene(ao.get3dScene());
    }
}
