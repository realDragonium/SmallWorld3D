package Controller;

import Enum.ApplicatieViewEnum;
import Model.ApplicatieModel;
import Objects.FXMLLOADER;
import Observer.ApplicatieObserver;
import View.GameView;
import View.HomeScreenView;
import View.LoginView;
import javafx.scene.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ApplicatieController {
    private ApplicatieModel appModel = new ApplicatieModel();

    private FXMLLOADER fxmlLoader = new FXMLLOADER();
    private Map<Class, Callable<?>> creators = new HashMap<>();

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
        creators.put(LoginView.class, (Callable<LoginView>) () -> new LoginView(loginCon, group));
        fxmlLoader.loader("/LoginScreen/Loginscherm.fxml", creators);
    }

    public void createHomeScreenController(Group group){
        hsCon = new HomeScreenController(this);
        creators.put(HomeScreenView.class, (Callable<HomeScreenView>) () -> new HomeScreenView(hsCon, group));
        fxmlLoader.loader("/HomeScreen/Homescreen.fxml", creators);
    }

    public void createGameController(Group group) {
        gameCon = new GameController(this);
        creators.put(GameView.class, (Callable<GameView>) () -> new GameView(gameCon, group));
        fxmlLoader.loader("/GameView.fxml", creators);
    }
}
