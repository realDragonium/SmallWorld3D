package Controller;

import Firebase.FirebaseGameObserver;
import Model.GameModel;
import Objects.FXMLLOADER;
import Observer.GameObserver;
import View.*;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.Group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import Enum.GameViewEnum;
import javafx.scene.transform.Translate;

public class GameController implements FirebaseGameObserver {

    private FirebaseGameController fbGame;
    Translate player1Pos = new Translate(600, 0, 0);

    private Controller3D con3d;
    private ApplicationController appCon;
    private FXMLLOADER fxmlLoader = new FXMLLOADER();
    private MapController mapCon;
    private ButtonController buttonCon;
    private TimerController timerCon;
    private VervallenController vervalCon;
    private InfoController infoCon;

    private GameModel model;
    private Map<String, PlayerController> players = new HashMap<>();
    private PlayerController currentPlayer;
    private RoundController roundCon;
    private TurnController turnCon;
    private AreaInformationController areaInfoCon;

    private String lobbyName;
    private VervallenController vervCon;
    private TimerController timeCon;
    private GameTimer gameTimer;
    private AttackController attCon;
    private ShopController shopCon;
    private PhaseController phaseCon;
    private RedeployingController redCon;
    private DiceController diceCon;

    public GameController(ApplicationController appCon) {
        this.appCon = appCon;
        model = new GameModel(8, 4);
        createControllers();
        fbGame.register("currentplayer", this);
//        startGame();
    }


    public void setPlayerPositions() {
        players.get("player1").setPlayerPosition(player1Pos);
        players.get("player1").addRaceFiche(con3d.createRaceFiche("ratten"));
        players.get("player1").addRaceFiche(con3d.createRaceFiche("ratten"));
    }

    public void create3dView(Group group) {
        new Map3DView(mapCon, group);
    }

    public void createMap2DView(Group group) {
        fxmlLoader.loader("/Map/UglyMap5.fxml", (Callable<Map2DView>) () -> new Map2DView(mapCon, group));
    }

    public void createPlayerView(Group group, String id) {
        PlayerController player = new PlayerController(id, this);
        players.put(id, player);
        fxmlLoader.loader("/PlayerView.fxml", (Callable<PlayerView>) () -> new PlayerView(id, group, player));
    }

    public void createUIOverlay(Group group) {
        fxmlLoader.loader("/UI/UIView.fxml", (Callable<UIView>) () -> new UIView(group));
    }

    public void createRoundView(Group group) {
        fxmlLoader.loader("/RoundView.fxml", (Callable<RoundView>) () -> new RoundView(group, roundCon));
    }

    public void createAreaInfoView(Group group) {
        fxmlLoader.loader("/AreaInfoView.fxml", (Callable<AreaInformationView>) () -> new AreaInformationView(group, areaInfoCon));
    }

    public void createTurnView(Group group) {
        fxmlLoader.loader("/TurnView.fxml", (Callable<TurnView>) () -> new TurnView(group, turnCon));
    }

    public void createButtonView(Group group) {
        fxmlLoader.loader("/ButtonView.fxml", (Callable<ButtonView>) () -> new ButtonView(group, buttonCon));
    }

    public void createShopView(Group group) {
        fxmlLoader.loader("/ShopView.fxml", (Callable<ShopView>) () -> new ShopView(group, shopCon));
    }

    public void createTimerView(Group group) {
        fxmlLoader.loader("/TimerView.fxml", (Callable<TimerView>) () -> new TimerView(group, timerCon));
    }

    public void createVervalView(Group group) {
        fxmlLoader.loader("/VervallenView.fxml", (Callable<VervallenView>) () -> new VervallenView(group, vervalCon));
    }

    public void createDiceView(Group group) {
        fxmlLoader.loader("/Dice/DiceView.fxml", (Callable<DiceView>) () -> new DiceView(group, diceCon));
    }

    public void createRedeployView(Group group) {
        fxmlLoader.loader("/RedeployingView.fxml", (Callable<RedeployingView>) () -> new RedeployingView(group, redCon));
    }

    public void createInfoView(Group group) {
        fxmlLoader.loader("/InfoScreen/InfoView.fxml", (Callable<InfoView>) () -> new InfoView(group, infoCon));
    }

    public void createPhaseView(Group group) {
        fxmlLoader.loader("/PhaseView.fxml", (Callable<PhaseView>) () -> new PhaseView(group, phaseCon));
    }


    private void createControllers() {
        fbGame = new FirebaseGameController("test", this);
        attCon = new AttackController(this);
        redCon = new RedeployingController(this);
        infoCon = new InfoController(this);
        diceCon = new DiceController(this);
        vervalCon = new VervallenController(this);
        timerCon = new TimerController(this);
        shopCon = new ShopController(this);
        buttonCon = new ButtonController(this);
        roundCon = new RoundController(this);
        areaInfoCon = new AreaInformationController(this);
        phaseCon = new PhaseController(this);
        mapCon = new MapController(this);
        turnCon = new TurnController(this);
    }



    public PlayerController getPlayer() {
        return currentPlayer;
    }

    void changePlayerTurn(String player) {
        currentPlayer = players.get(player);
//        setGameTurn();
    }


//    private void setGameTurn(){
//        phaseController.newTurn(currentPlayer);
//    }

    private PlayerController getPlayer(String id) {
        return players.get(id);
    }

    public PlayerController getCurrentPlayer() {
        return players.get("player1");
//        return currentPlayer;
    }

    int getNumberOfPlayers(){
        return model.getNumberOfPlayers();
    }

    RoundController getRoundCon() {
        return roundCon;
    }

    public ShopController getShopCon() {
        return shopCon;
    }

    TurnController getTurnCon() {
        return turnCon;
    }

    MapController getMapCon() {
        return mapCon;
    }

    public VervallenController getVervCon() {
        return vervCon;
    }

    public AttackController getAttCon() {
        return attCon;
    }

    DiceController getDiceCon() {
        return diceCon;
    }

    PhaseController getPhaseController() {
        return phaseCon;
    }

    void endGame() {
        model.gameEnded = true;
    }

    boolean isGameOver() {
        return model.gameEnded;
    }

    private void startGame() {
        phaseCon = new PhaseController(this);
    }

    private void createGameTimer() {
        gameTimer = new GameTimer(this, 30);
    }

    public void nextTurn() {
        turnCon.nextTurn();
        System.out.println("next Turn!");
    }

    public void nextRound() {
        roundCon.nextRound();
        System.out.println("next Round!");
    }

    void setCurrentPlayer(int i) {
        currentPlayer = getPlayer("player" + i);
    }

    GameTimer getGameTimer() {
        return gameTimer;
    }

    TimerController getTimer() {
        return timeCon;
    }

    public String getLobbyname() {
        return lobbyName;
    }

    public void addToGameView(GameViewEnum go) {
        model.addActiveView(go);
    }

    public void removeFromGameView(GameViewEnum go) {
        model.removeActiveView(go);
    }

    void changeGameView(List<GameViewEnum> views) {
        model.changeGameView(views);
    }

    public void nextPhase() {
        phaseCon.nextPhase();
    }

    AreaInformationController getAreaInfoCon() {
        return areaInfoCon;
    }

    public void register(GameObserver go) {
        model.register(go);
    }

    FirebaseGameController getFireBase() {
        return fbGame;
    }


    @Override
    public void update(DocumentSnapshot ds) {

    }
}
