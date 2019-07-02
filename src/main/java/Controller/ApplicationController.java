package Controller;

import Enum.ApplicationViewEnum;
import Model.ApplicationModel;
import Objects.SpecialFXMLLoader;
import Observer.ApplicationObserver;
import View.GameView;
import View.HomeScreenView;
import View.LoginView;
import javafx.scene.Group;

import java.util.concurrent.Callable;

public class ApplicationController {
    private ApplicationModel appModel = new ApplicationModel();
    private SpecialFXMLLoader fxmlLoader = new SpecialFXMLLoader();

    private LoginController loginCon;
    private HomeScreenController hsCon;
    private GameController gameCon;

    public void setActiveView(ApplicationViewEnum view){
        appModel.setCurrentView(view);
    }

    public void register(ApplicationObserver ao){
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
//        new GameView(gameCon, group);
        fxmlLoader.loader("/GameView.fxml", (Callable<GameView>) () -> new GameView(gameCon, group));
    }

}
