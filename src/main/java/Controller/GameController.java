package Controller;

import Managers.SceneManager;
import Model.GameModel;
import Objects.FXMLLOADER;
import View.Map2DView;
import View.PlayerView;
import View.RoundView;
import View.TurnView;
import javafx.scene.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class GameController {
    private ApplicatieController appCon;
    private FXMLLOADER fxmlLoader = new FXMLLOADER();
    private Map<Class, Callable<?>> creators = new HashMap<>();
    private Map2DController mapCon;



    private GameModel model;
    private Map<String, PlayerController> players = new HashMap<>();
    private PlayerController currentPlayer;
    private RoundController roundCon;
    private TurnController turnCon;

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
        SceneManager.getInstance().createGameView(this);
        SceneManager.getInstance().makeMap();
        createGameParts();
        startGame();
        createGameTimer();
    }

    public GameController(ApplicatieController appCon){
        this.appCon = appCon;

    }

    public void createMap2DView(Group group){
        mapCon = new Map2DController(this);
        creators.put(Map2DView.class, (Callable<Map2DView>)() -> new Map2DView(mapCon, group));
        fxmlLoader.loader("/UglyMap.fxml", creators);
    }

    public void createPlayerView(Group group, String id){
        PlayerController player = new PlayerController(id, this);
        players.put(id, player);
        creators.put(PlayerView.class, (Callable<PlayerView>)() -> new PlayerView(id, group, player));
        fxmlLoader.loader("/PlayerView.fxml", creators);
    }

    public void createRoundView(Group group) {
        roundCon = new RoundController(this);
        creators.put(RoundView.class, (Callable<RoundView>)() -> new RoundView(group, roundCon));
        fxmlLoader.loader("/RoundView.fxml", creators);
    }

    public void createTurnView(Group group) {
        turnCon = new TurnController(this);
        creators.put(TurnView.class, (Callable<TurnView>)() -> new TurnView(group, turnCon));
        fxmlLoader.loader("/TurnView.fxml", creators);
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

    void createGameParts() {
        createPlayer();
        createShop();
        createVerval();
//        SceneManager.getInstance().loadSmallworld();
        createTurnsAndRounds();
        diceCon = new DiceController();
        new InfoController();
        new KnoppenController(this);
        timeCon = new TimerController(getGameTurn());
        redCon = new RedeployingController(this);

        createAttCon();
        mapCon = new Map2DController(this);
    }



    private void createVerval() {
        vervCon = new VervallenController(this);
    }

    private void createPlayer(){
        players.put("player0", new PlayerController("player0", this));
        players.put("player1", new PlayerController("player1", this));
        players.put("player2", new PlayerController("player2", this));
        players.put("player3", new PlayerController("player3", this));
        players.put("player4", new PlayerController("player4", this));
        myPlayer = players.get(myPlayerId);
    }

    private void createShop(){
        shopCon = new ShopController(this);
    }

    private void createTurnsAndRounds(){
        roundCon = new RoundController(this);
        turnCon = new TurnController(this);
    }

    private void setGameTurn(){
        gameTurn.newTurn(currentPlayer);
    }

    PlayerController getPlayer(String id){
        return players.get(id);
    }

    public PlayerController getCurrentPlayer(){
        return currentPlayer;
    }

    private void createAttCon(){
        attCon = new AttackController(this);
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

    void createGameTimer(){
        gameTimer = new GameTimer(this, 30);
    }

    void nextTurn() {

        turnCon.nextTurn();
    }

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

}
