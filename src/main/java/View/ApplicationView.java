package View;

import Controller.ApplicationController;
import Enums.ApplicationViewEnum;
import Observable.ApplicationObservable;
import Observer.ApplicationObserver;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ApplicationView implements ApplicationObserver {
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
        appCon.setActiveView(ApplicationViewEnum.GAME);
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
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void setActive(ApplicationViewEnum view) {
        root.getChildren().clear();
        root.getChildren().add(groups.get(view.getStringValue()));
    }

    @Override
    public void update(ApplicationObservable ao) {
        setActive(ao.getCurrentView());
    }
}
