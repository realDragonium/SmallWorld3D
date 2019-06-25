package View;

import Controller.ApplicatieController;
import Enum.ApplicatieViewEnum;
import Observable.ApplicatieObservable;
import Observer.ApplicatieObserver;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ApplicatieView implements ApplicatieObserver {
    private Scene scene;
    private Stage primaryStage;
    private Group root = new Group();
    private Map<String, Group> groups = new HashMap<>();

    private ApplicatieController appCon = new ApplicatieController();

    public ApplicatieView(Stage primaryStage){
        this.primaryStage = primaryStage;
        root.getChildren().add(new Button("ApplicatieView!"));

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

    @Override
    public void update(ApplicatieObservable ao) {
        setActive(ao.getCurrentView());
    }
}
