package Controller;

import Enums.ApplicationViewEnum;
import Firebase.FirebaseLobbyService;
import Firebase.FirebaseService;
import Model.ApplicationModel;
import Objects.SpecialFXMLLoader;
import Observer.ApplicationObserver;
import View.*;
import com.google.cloud.firestore.Firestore;
import javafx.scene.Group;

import java.util.concurrent.Callable;

public class ApplicationController {
    private ApplicationModel appModel = new ApplicationModel();
    private SpecialFXMLLoader fxmlLoader = new SpecialFXMLLoader();

    private LoginController loginCon;
    private HomeScreenController hsCon;
    private GameController gameCon;
    private LobbyController lobbyCon;
    private InLobbyController inLobbyCon;
    private FirebaseLobbyController lobbyFB;
    private FirebaseService fbService;

    public ApplicationController(){
        fbService = new FirebaseService();
        new Thread(fbService).start();
    }

    public void setActiveView(ApplicationViewEnum view){
        appModel.setCurrentView(view);
    }

    public void register(ApplicationObserver ao){
        appModel.register(ao);
    }

    public void createLobbyFireBase(){
//        lobbyFB = new FirebaseLobbyService();
//        lobbyFB.startFBService();
        lobbyFB = new FirebaseLobbyController(this);
        new Thread(lobbyFB).start();
    }

    public void createLoginController(Group group){
        loginCon = new LoginController(this);
        fxmlLoader.loader("/LoginScreen/Loginscherm.fxml", (Callable<LoginView>) () -> new LoginView(loginCon, group));
    }

    public void createHomeScreenController(Group group){
        hsCon = new HomeScreenController(this);
        fxmlLoader.loader("/HomeScreen/Homescreen.fxml", (Callable<HomeScreenView>) () -> new HomeScreenView(hsCon, group));
    }

    public void createLobbyController(Group group){
        lobbyCon = new LobbyController(this);
        fxmlLoader.loader("/Lobbies/lobbyScreen.fxml", (Callable<LobbyView>) () -> new LobbyView(lobbyCon, group));
    }

    public void createInLobbyController(Group group){
        inLobbyCon = new InLobbyController(this);
        fxmlLoader.loader("/Lobbies/InLobbyScreen.fxml", (Callable<InLobbyView>) () -> new InLobbyView(inLobbyCon, group));
    }

    public void createGameController(Group group) {
        gameCon = new GameController(this);
        new GameView(gameCon, group);
        fxmlLoader.loader("/GameView.fxml", (Callable<GameView>) () -> new GameView(gameCon, group));
    }

    FirebaseLobbyController getLobbyFireBase() {
        return lobbyFB;
    }

    InLobbyController getInLobbyCon(){
        return  inLobbyCon;
    }

    public void startGame(){
        //createGameController(groups.get("game"));
    }

    public FirebaseService getFirestore() {
        return fbService;
    }
}
