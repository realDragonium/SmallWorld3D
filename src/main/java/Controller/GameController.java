package Controller;

import Model.GameModel;
import Objects.FXMLLOADER;
import Observer.GameObserver;
import View.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import Enum.GameViewEnum;
import javafx.scene.Node;
import javafx.scene.transform.Translate;

public class GameController {

    private Group addable3d = new Group();

    private FirebaseGameController fbGame;
    Translate player1Pos = new Translate(600, 0, 0);
    Translate player1Pos2d = new Translate(600, 900);

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
    private GameTurn gameTurn;
    private PlayerController myPlayer;
    private RedeployingController redCon;
    private String myPlayerId;
    private DiceController diceCon;

    public GameController(String lobbyName, String playerID) {
        myPlayerId = playerID;
        model = new GameModel(8, 8);
        this.lobbyName = lobbyName;
        setMuFirebaseStufF();
        startGame();
    }

    public GameController(ApplicationController appCon){
        this.appCon = appCon;
        model = new GameModel(8, 8);
        gameTurn = new GameTurn(this);
        fbGame = new FirebaseGameController("test");
        mapCon = new MapController(this);
//        startGame();
    }


    public void setPlayerPositions(){
        players.get("player1").setPlayer3dPosition(player1Pos);
        players.get("player1").setPlayer2dPosition(player1Pos2d);
//        players.get("player1").addRaceFiche(con3d.createRaceFiche("ratten"));
//        players.get("player1").addRaceFiche(con3d.createRaceFiche("ratten"));
    }

    public void create3dView(Group group){
        new Map3DView(mapCon, group);
    }

    public void createMap2DView(Group group){
        fxmlLoader.loader("/Map/UglyMap5.fxml", (Callable<Map2DView>)() -> new Map2DView(mapCon, group));
    }

    public void createPlayerView(Group group, String id){
        PlayerController player = new PlayerController(id, this);
        players.put(id, player);
        fxmlLoader.loader("/PlayerView.fxml", (Callable<PlayerView>)() -> new PlayerView(id, group, player));
    }

    public void createUIOverlay(Group group) {
        fxmlLoader.loader("/UI/UIView.fxml", (Callable<UIView>)() -> new UIView(group));
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


    public Group createSpecialPropFiche(String specialProp) {
        Group group = new Group();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/3dObjects/" + specialProp + ".fxml"));
            Node node = fxmlLoader.load();
            group.getChildren().add(node);

            addable3d.getChildren().add(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return group;

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

    public void createCombinationView(Group group, CombinationController combiCon) {
        fxmlLoader.loader("/CombinationView.fxml", (Callable<CombinationView>)() -> new CombinationView(group, combiCon));
    }

    public void createRaceFiche(FicheController con){
        new fiche3dView(con, addable3d);
    }




    private void setMuFirebaseStufF(){
//        fb.setGame(lobbyName);
//        Map<String, Object> info = new HashMap<>();
//        info.put("Name", app.getAccountCon().getAccountName());
//        info.put("fiches", 0);
//        info.put("points", 5);
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
        return players.get("player1");
//        return currentPlayer;

    }

    RoundController getRoundCon(){
        return roundCon;
    }

    public ShopController getShopCon(){return shopCon;}

    TurnController getTurnCon(){
        return turnCon;
    }

    MapController getMapCon(){
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

    public void set3dGroup(Group group){
        group.getChildren().add(addable3d);
    }

    public void addToGameView(GameViewEnum go){
        model.addActiveView(go);
    }

    public void removeFromGameView(GameViewEnum go){
        model.removeActiveView(go);
    }

    void changeGameView(List<GameViewEnum> views){
        model.changeGameView(views);
    }

    public void nextPhase(){
        gameTurn.nextPhase();
    }

    AreaInformationController getAreaInfoCon() {
        return areaInfoCon;
    }

    public void register(GameObserver go){
        model.register(go);
    }

    FirebaseGameController getFireBase() {
        return fbGame;
    }


}
