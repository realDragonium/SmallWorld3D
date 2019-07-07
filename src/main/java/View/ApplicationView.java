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

    private ApplicationController appCon;

    public ApplicationView(Stage primaryStage){
        this.primaryStage = primaryStage;
        appCon = new ApplicationController();
        createViews();
        appCon.register(this);
        setStartScreen();
        startPrimaryStage();
    }

    private void setStartScreen(){
        setActive(ApplicationViewEnum.LOGIN);
    }

    private void createViews(){
        appCon.createLoginController();
        appCon.createHomeScreenController();
        appCon.createLobbyController();
        appCon.createInLobbyController();
        //appCon.createGameController();
    }

    private void startPrimaryStage(){
        scene = new Scene(root);

        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true);
        primaryStage.show();
//        primaryStage.setMaximized(true);
        primaryStage.setTitle("Small World - Group 7b");

        primaryStage.setOnCloseRequest(event -> {
            appCon.logout();
        });
    }

    private void setActive(ApplicationViewEnum view) {
        root.getChildren().clear();
        root.getChildren().add(view.getGroup());
    }

    @Override
    public void update(ApplicationObservable ao) {
        setActive(ao.getCurrentView());
    }
}
