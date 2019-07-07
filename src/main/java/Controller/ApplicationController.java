package Controller;

import Enums.ApplicationViewEnum;
import Enums.PowerEnum;
import Enums.RaceEnum;
import Firebase.FirebaseService;
import Model.ApplicationModel;
import Objects.SpecialFXMLLoader;
import Observer.ApplicationObserver;
import View.*;
import javafx.application.Platform;

import java.util.*;
import java.util.concurrent.Callable;

public class ApplicationController {
    private ApplicationModel appModel = new ApplicationModel();
    private SpecialFXMLLoader fxmlLoader = new SpecialFXMLLoader();

    private AccountController account = new AccountController("host");
    private LoginController loginCon;
    private LeaderboardController leaderCon;
    private HomeScreenController hsCon;
    private GameController gameCon;
    private LobbyController lobbyCon;
    private InLobbyController inLobbyCon;
    FirebaseService fbService;

    public ApplicationController() {
        fbService = new FirebaseService();
        new Thread(fbService).start();

    }

    public void setActiveView(ApplicationViewEnum view) {
        appModel.setCurrentView(view);
    }

    public void register(ApplicationObserver ao) {
        appModel.register(ao);
    }

    public void createLoginController() {
        loginCon = new LoginController(this);
        fxmlLoader.loader("/LoginScreen/Loginscherm.fxml", (Callable<LoginView>) () -> new LoginView(loginCon, ApplicationViewEnum.LOGIN.getGroup()));
    }

    public void createHomeScreenController() {
        hsCon = new HomeScreenController(this);
        fxmlLoader.loader("/HomeScreen/Homescreen.fxml", (Callable<HomeScreenView>) () -> new HomeScreenView(hsCon, ApplicationViewEnum.HOMESCREEN.getGroup()));
    }

    public void createLobbyController() {
        lobbyCon = new LobbyController(this);
        fxmlLoader.loader("/Lobbies/lobbyScreen.fxml", (Callable<LobbyView>) () -> new LobbyView(lobbyCon, ApplicationViewEnum.LOBBY.getGroup()));
    }

    public void createInLobbyController() {
        inLobbyCon = new InLobbyController(this);
        fxmlLoader.loader("/Lobbies/InLobbyScreen.fxml", (Callable<InLobbyView>) () -> new InLobbyView(inLobbyCon, ApplicationViewEnum.INLOBBY.getGroup()));
    }

    public void createLeaderBoard(){
        leaderCon = new LeaderboardController(this);
        fxmlLoader.loader("/Leaderboard/LeaderboardScreen.fxml", (Callable<LeaderboardView>) () -> new LeaderboardView(leaderCon, ApplicationViewEnum.LEADER.getGroup()));
    }

    private void createGameController(HashMap info) {
        gameCon = new GameController(this);
        fxmlLoader.loader("/GameView.fxml", (Callable<GameView>) () -> new GameView(gameCon, ApplicationViewEnum.GAME.getGroup()));
        gameCon.setLobbyInfo(info);
        gameCon.startFirebaseConnection(fbService.getfbGame());
        setActiveView(ApplicationViewEnum.GAME);
    }

    void showLeaderBord(List<PlayerController> players){
        leaderCon.sortPlayers(players);
        setActiveView(ApplicationViewEnum.LEADER);
    }

    AccountController getAccount(){
        return account;
    }

    FirebaseLobbyController getLobbyFireBase() {
        return fbService.getfbLobby();
    }

    InLobbyController getInLobbyCon() {
        return inLobbyCon;
    }

    void startGame(HashMap info) {
        System.out.println("progress: "+info.get("inProgress"));
        createGameController(info);
        if((Boolean) info.get("inProgress")) return;
        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> beginGame());
            }
        };
        new Timer().schedule(start, 3000);
    }

    void beginGame() {
        fbService.getfbGame().startGame();
    }

    public FirebaseService getFirestore() {
        return fbService;
    }

    public LobbyController getLobbyCon() {
        return lobbyCon;
    }

    void setAccount(AccountController account) {
        this.account = account;
    }

    public void logout() {
        fbService.getfbLogin().logout(account.getAccountName());
    }


    public void createLeaderBordTest(){
        List<PlayerController> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new PlayerController("test"+i));
            players.get(i).addPoints(5*i + 5);
        }

        showLeaderBord(players);
    }
}
