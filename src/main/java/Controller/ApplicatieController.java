package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import Enum.ApplicatieViewEnum;
import Model.ApplicatieModel;
import Observer.ApplicatieObserver;
import View.HomeScreenView;
import View.LoginView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ApplicatieController {
    private ApplicatieModel appModel = new ApplicatieModel();

    private Map<Class, Callable<?>> creators = new HashMap<>();

    private LoginController loginCon;
    private HomeScreenController hsCon;

    public void setActiveView(ApplicatieViewEnum view){
        appModel.setCurrentView(view);
    }

    public void register(ApplicatieObserver ao){
        appModel.register(ao);
    }

    public void createLoginController(Group group){
        loginCon = new LoginController(this);
        creators.put(LoginView.class, () -> new LoginView(loginCon, group));
        FXMLLOADER("/LoginScreen/Loginscherm.fxml");
    }

    public void createHomeScreenController(Group group){
        hsCon = new HomeScreenController(this);
        creators.put(HomeScreenView.class, (Callable<HomeScreenView>) () -> new HomeScreenView(hsCon, group));
        FXMLLOADER("/HomeScreen/Homescreen.fxml");
    }

    private void FXMLLOADER(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        fxmlLoader.setControllerFactory(param -> {
            Callable<?> callable = creators.get(param);
            try {
                return callable.call();
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        });
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
