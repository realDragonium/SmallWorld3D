package Controller;

import Enum.ApplicatieViewEnum;
import Model.ApplicationModel;
import Objects.FXMLLOADER;
import Observer.ApplicatieObserver;
import View.GameView;
import View.HomeScreenView;
import View.LoginView;
import javafx.scene.Group;
import javafx.scene.SubScene;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ApplicationController {
    private ApplicationModel appModel = new ApplicationModel();
    private FXMLLOADER fxmlLoader = new FXMLLOADER();

    private LoginController loginCon;
    private HomeScreenController hsCon;
    private GameController gameCon;

    public void setActiveView(ApplicatieViewEnum view){
        appModel.setCurrentView(view);
    }

    public void register(ApplicatieObserver ao){
        appModel.register(ao);
    }

    public void createLoginController(Group group){
        loginCon = new LoginController(this);
        fxmlLoader.loader("/LoginScreen/Loginscherm.fxml", (Callable<LoginView>) () -> new LoginView(loginCon, group));
    }

    public void createHomeScreenController(Group group){
        hsCon = new HomeScreenController(this);
        fxmlLoader.loader("/HomeScreen/Homescreen.fxml", (Callable<HomeScreenView>) () -> new HomeScreenView(hsCon, group));
    }

    public void createGameController(Group group) {
        gameCon = new GameController(this);
        fxmlLoader.loader("/GameView.fxml", (Callable<GameView>) () -> new GameView(gameCon, group));
    }

    public void addSubScene(SubScene scene) {
        appModel.addSubScene(scene);
    }
}
