package Controller;

import Model.GameModel;
import Objects.FXMLLOADER;
import Observer.GameObserver;
import View.*;
import javafx.scene.Group;
import javafx.scene.SubScene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import Enum.GameViewEnum;

public class GameController {

    SubScene subScene3d;

    private Controller3D con3d;
    private ApplicationController appCon;
    private FXMLLOADER fxmlLoader = new FXMLLOADER();
    private Map2DController mapCon;
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
    private GameTurn gameTurn;
    private PlayerController myPlayer;
    private RedeployingController redCon;
    private String myPlayerId;
    private DiceController diceCon;

//    private Applicatie app = SceneManager.getInstance().getApp();
//    private FirebaseServiceOwn fb = app.getFirebaseService();

    public GameController(String lobbyName, String playerID) {
        myPlayerId = playerID;
        model = new GameModel(8, 8);
        this.lobbyName = lobbyName;
        setMuFirebaseStufF();
//        SceneManager.getInstance().createGameView(this);
//        SceneManager.getInstance().makeMap();
        startGame();
    }

    public GameController(ApplicationController appCon){
        this.appCon = appCon;
        model = new GameModel(8, 8);
        gameTurn = new GameTurn(this);
//        startGame();
    }

    public void create3dView(Group group){
        con3d = new Controller3D(this, group);
    }

    public void createMap2DView(Group group){
        mapCon = new Map2DController(this);
        fxmlLoader.loader("/UglyMap.fxml", (Callable<Map2DView>)() -> new Map2DView(mapCon, group));
    }

    public void createPlayerView(Group group, String id){
        PlayerController player = new PlayerController(id, this);
        players.put(id, player);
        fxmlLoader.loader("/PlayerView.fxml", (Callable<PlayerView>)() -> new PlayerView(id, group, player));
    }

    public void createRoundView(Group group) {
        roundCon = new RoundController(this);
        fxmlLoader.loader("/RoundView.fxml", (Callable<RoundView>)() -> new RoundView(group, roundCon));
    }

    public void createAreaInfoView(Group group) {
        areaInfoCon = new AreaInformationController(this);
        fxmlLoader.loader("/AreaInfoView.fxml", (Callable<AreaInformationView>)() -> new AreaInformationView(group, areaInfoCon));
    }

    public void createTurnView(Group group) {
        turnCon = new TurnController(this);
        fxmlLoader.loader("/TurnView.fxml", (Callable<TurnView>)() -> new TurnView(group, turnCon));
    }

    public void createButtonView(Group group) {
        buttonCon = new ButtonController(this);
        fxmlLoader.loader("/ButtonView.fxml", (Callable<ButtonView>)() -> new ButtonView(group, buttonCon));
    }

    public void createShopView(Group group) {
        shopCon = new ShopController(this);
        fxmlLoader.loader("/ShopView.fxml", (Callable<ShopView>)() -> new ShopView(group, shopCon));
    }

    public void createTimerView(Group group) {
        timerCon = new TimerController(this);
        fxmlLoader.loader("/TimerView.fxml", (Callable<TimerView>)() -> new TimerView(group, timerCon));
    }

    public void createVervalView(Group group) {
        vervalCon = new VervallenController(this);
        fxmlLoader.loader("/VervallenView.fxml", (Callable<VervallenView>)() -> new VervallenView(group, vervalCon));
    }

    public void createDiceView(Group group) {
        diceCon = new DiceController(this);
        fxmlLoader.loader("/Dice/DiceView.fxml", (Callable<DiceView>)() -> new DiceView(group, diceCon));
    }

    public void createRedeployView(Group group) {
        redCon = new RedeployingController(this);
        fxmlLoader.loader("/RedeployingView.fxml", (Callable<RedeployingView>)() -> new  RedeployingView(group, redCon));
    }

    public void createInfoView(Group group) {
        infoCon = new InfoController(this);
        fxmlLoader.loader("/InfoScreen/InfoView.fxml", (Callable<InfoView>)() -> new InfoView(group, infoCon));
    }

    public void createAttackView(Group group) {
        attCon = new AttackController(this);
        fxmlLoader.loader("/AttackView.fxml", (Callable<AttackView>)() -> new AttackView(group, attCon));
    }




    private void setMuFirebaseStufF(){
//        fb.setGame(lobbyName);
//        Map<String, Object> info = new HashMap<>();
//        info.put("Name", app.getAccountCon().getAccountName());
//        info.put("fiches", 0);
//        info.put("punten", 5);
//        fb.registerPlayer(myPlayerId, info);
    }



    String getMyPlayerId(){
        return myPlayerId;
    }

    public PlayerController getPlayer(){
        return currentPlayer;
    }

    void changePlayerTurn(String player){
        currentPlayer = players.get(player);
        setGameTurn();
    }


    private void setGameTurn(){
        gameTurn.newTurn(currentPlayer);
    }

    private PlayerController getPlayer(String id){
        return players.get(id);
    }

    public PlayerController getCurrentPlayer(){
        return currentPlayer;
    }

    RoundController getRoundCon(){
        return roundCon;
    }

    public ShopController getShopCon(){return shopCon;}

    TurnController getTurnCon(){
        return turnCon;
    }

    Map2DController getMapCon(){
        return mapCon;
    }

    public VervallenController getVervCon(){return vervCon;}

    public AttackController getAttCon(){
        return attCon;
    }

    DiceController getDiceCon() {return diceCon;}

    GameTurn getGameTurn() { return gameTurn;}

    void endGame(){
        model.gameEnded = true;
    }

    boolean isGameOver(){
        return model.gameEnded;
    }

    private void startGame(){
        gameTurn = new GameTurn(this, currentPlayer);
    }

    private void createGameTimer(){
        gameTimer = new GameTimer(this, 30);
    }

    void nextTurn() { turnCon.nextTurn(); }

    PlayerController getMyPlayer() {
        return myPlayer;
    }

    void setCurrentPlayer(int i) {
        currentPlayer = getPlayer("player" + i);
    }

    GameTimer getGameTimer() {
        return gameTimer;
    }

    TimerController getTimer(){
        return timeCon;
    }

    public String getLobbyname(){
        return lobbyName;
    }

    public void addToGameView(GameViewEnum go){
        model.addActiveView(go);
    }

    public void removeFromGameView(GameViewEnum go){
        model.removeActiveView(go);
    }

    public void changeGameView(List<GameViewEnum> views){
        model.changeGameView(views);
    }

    public void nextPhase(){
        gameTurn.nextPhase();
    }

    public AreaInformationController getAreaInfoCon() {
        return areaInfoCon;
    }

    public void register(GameObserver go){
        model.register(go);
    }

}
