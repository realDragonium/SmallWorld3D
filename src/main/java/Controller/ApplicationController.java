package Controller;

import Enums.ApplicationViewEnum;
import Enums.PowerEnum;
import Enums.RaceEnum;
import Firebase.FirebaseService;
import Model.ApplicationModel;
import Objects.SpecialFXMLLoader;
import Observer.ApplicationObserver;
import View.*;

import java.util.*;
import java.util.concurrent.Callable;

public class ApplicationController {
    private ApplicationModel appModel = new ApplicationModel();
    private SpecialFXMLLoader fxmlLoader = new SpecialFXMLLoader();

    private LoginController loginCon;
    private HomeScreenController hsCon;
    private GameController gameCon;
    private LobbyController lobbyCon;
    private InLobbyController inLobbyCon;
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
        return fbService.getfbLobby();
    }

    InLobbyController getInLobbyCon(){
        return  inLobbyCon;
    }

    void startGame(){
        createGameController();

        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                beginGame();
            }
        };

//        new Timer().schedule(start, 3000);
    }

    void beginGame() {
        List<String> races = new ArrayList<>();
        List<String> powers = new ArrayList<>();
        Arrays.asList(PowerEnum.values()).forEach(power -> powers.add(power.getPower().getName()));
        Arrays.asList(RaceEnum.values()).forEach(race -> races.add(race.getRace().getName()));
        races.remove("losttribes");
        for (int i = 0; i < 6; i++) {
            String race = races.get((int) (Math.random() * races.size()));
            String power = powers.get((int) (Math.random() * powers.size()));
            races.remove(race);
            powers.remove(power);
            fbService.getfbGame().addCombiAction(race, power);
        }
        fbService.getfbGame().startGame();
    }

    public FirebaseService getFirestore() {
        return fbService;
    }
}
