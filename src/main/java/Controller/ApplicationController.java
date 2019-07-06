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

import java.util.Timer;
import java.util.TimerTask;
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
        lobbyFB = fbService.getfbLobby();
    }

    public void createLoginController(){
        loginCon = new LoginController(this);
        fxmlLoader.loader("/LoginScreen/Loginscherm.fxml", (Callable<LoginView>) () -> new LoginView(loginCon, ApplicationViewEnum.LOGIN.getGroup()));
    }

    public void createHomeScreenController(){
        hsCon = new HomeScreenController(this);
        fxmlLoader.loader("/HomeScreen/Homescreen.fxml", (Callable<HomeScreenView>) () -> new HomeScreenView(hsCon, ApplicationViewEnum.HOMESCREEN.getGroup()));
    }

    public void createLobbyController(){
        lobbyCon = new LobbyController(this);
        fxmlLoader.loader("/Lobbies/lobbyScreen.fxml", (Callable<LobbyView>) () -> new LobbyView(lobbyCon, ApplicationViewEnum.LOBBY.getGroup()));
    }

    public void createInLobbyController(){
        inLobbyCon = new InLobbyController(this);
        fxmlLoader.loader("/Lobbies/InLobbyScreen.fxml", (Callable<InLobbyView>) () -> new InLobbyView(inLobbyCon, ApplicationViewEnum.INLOBBY.getGroup()));
    }

    public void createGameController() {
        gameCon = new GameController(this);
//        new GameView(gameCon, ApplicationViewEnum.GAME.getGroup());
        fxmlLoader.loader("/GameView.fxml", (Callable<GameView>) () -> new GameView(gameCon, ApplicationViewEnum.GAME.getGroup()));
        gameCon.startFirebaseConnection(fbService.getfbGame());
    }

    FirebaseLobbyController getLobbyFireBase() {
        return lobbyFB;
    }

    InLobbyController getInLobbyCon(){
        return  inLobbyCon;
    }

    void startGame(){
        createGameController();

        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                fbService.getfbGame().startGame();
            }
        };

//        new Timer().schedule(start, 3000);
    }

    public FirebaseService getFirestore() {
        return fbService;
    }
}
