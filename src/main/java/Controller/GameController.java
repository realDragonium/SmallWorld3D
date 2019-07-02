package Controller;

import Model.GameModel;
import Objects.SpecialFXMLLoader;
import Observer.GameObserver;
import View.*;
import javafx.scene.Group;
import Enum.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import Enum.GameViewEnum;
import javafx.scene.transform.Translate;

public class GameController {

    private FirebaseGameController fbGame;
    Translate playerPos2d = new Translate(600, 900);

    Translate player1Pos = new Translate(0, 0, -600);
    Translate player2Pos = new Translate(-600, 0, 0);
    Translate player3Pos = new Translate(0, 0, 600);
    Translate player4Pos = new Translate(600, 0, 0);

    private ApplicationController appCon;
    private MapController mapCon;
    private ButtonController buttonCon;
    private TimerController timerCon;
    private DeclineController declineCon;
    private InfoController infoCon;
    private NotificationController notiCon;


    private GameModel model;
    private Map<String, PlayerController> players = new HashMap<>();
    private PlayerController currentPlayer;
    private RoundController roundCon;
    private TurnController turnCon;
    private AreaInformationController areaInfoCon;


    private String lobbyName;
    private DeclineController vervCon;
    private TimerController timeCon;
    private AttackController attCon;
    private GameTimer gameTimer;
    private ShopController shopCon;
    private PhaseController phaseCon;
    private RedeployingController redCon;
    private DiceController diceCon;

    public GameController(ApplicationController appCon) {
        this.appCon = appCon;
        int numberOfPlayers = 4;
        model = new GameModel(8, createPlayers(numberOfPlayers));
        createUIOverlay();
        createControllers();
    }

    private List<PlayerController> createPlayers(int numberOfPlayers){
        List<PlayerController> players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++)
            players.add(new PlayerController(i, this));

        return players;
    }


    public void startGame(){
        turnCon.nextTurn();
        fbGame.activateListener();
    }

    public void setPlayerPositions(){
        model.getPlayer(0).setPlayer3dPosition(player1Pos);
        model.getPlayer(0).setPlayer2dPosition(playerPos2d);
        model.getPlayer(1).setPlayer3dPosition(player2Pos);
        model.getPlayer(1).setPlayer2dPosition(playerPos2d);
        model.getPlayer(2).setPlayer3dPosition(player3Pos);
        model.getPlayer(2).setPlayer2dPosition(playerPos2d);
        model.getPlayer(3).setPlayer3dPosition(player4Pos);
        model.getPlayer(3).setPlayer2dPosition(playerPos2d);
    }

    public void create3dView(){
        new Map3DView(mapCon);
    }

//    public void createMap2DView(Group group){
//        new SpecialFXMLLoader().loader("/Map/UglyMap5.fxml", (Callable<Map2DView>)() -> new Map2DView(mapCon, group));
//    }



    private void createUIOverlay() {
        new SpecialFXMLLoader().loader("/UI/UIView.fxml", (Callable<UIView>) () -> new UIView(GameViewEnum.UIOVERLAY.getGroup()));
    }

    private void createControllers() {
        fbGame = new FirebaseGameController("test", this);
        new Thread(fbGame).start();
        redCon = new RedeployingController(this);
        attCon = new AttackController(this);
        infoCon = new InfoController(this);
        diceCon = new DiceController(this);
        declineCon = new DeclineController(this);
        timerCon = new TimerController(this);
        shopCon = new ShopController(this);
        buttonCon = new ButtonController(this);
        roundCon = new RoundController(this);
        areaInfoCon = new AreaInformationController(this);
        phaseCon = new PhaseController(this);
        mapCon = new MapController(this);
        turnCon = new TurnController(this);
        notiCon = new NotificationController(this);
    }


    public PlayerController getPlayer(int id){
        return model.getPlayer(id);
    }

    PlayerController getCurrentPlayer(){
        return turnCon.getCurrentPlayer();
    }

    int getNumberOfPlayers(){
        return model.getNumberOfPlayers();
    }

    RoundController getRoundCon(){
        return roundCon;
    }

    public ShopController getShopCon() {
        return shopCon;
    }

    TurnController getTurnCon(){
        return turnCon;
    }

    MapController getMapCon(){
        return mapCon;
    }

    public DeclineController getVervCon(){
        return vervCon;
    }

    DiceController getDiceCon(){
        return diceCon;
    }

    PhaseController getPhaseCon(){
        return phaseCon;
    }

    void endGame(){
        model.gameEnded = true;
    }

    boolean isGameOver(){
        return model.gameEnded;
    }

    private void createGameTimer(){
        gameTimer = new GameTimer(this, 30);
    }

    void nextTurn() {
        turnCon.nextTurn();
    }

    GameTimer getGameTimer() {
        return gameTimer;
    }

    TimerController getTimer(){
        return timeCon;
    }


    void addToGameView(GameViewEnum go){
        model.addActiveView(go);
    }

    void removeFromGameView(GameViewEnum go){
        model.removeActiveView(go);
    }

    void changeGameView(List<GameViewEnum> views){
        model.changeGameView(views);
    }

    public void nextPhase(){
        phaseCon.nextPhase();
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

    void createRandomShopItem(){
        shopCon.createRandomShopItem();
    }

    List<PlayerController> getPlayers() {
        return model.getPlayers();
    }

    int imPlayer(){
        return model.imPlayer();
    }

    public void setPhase(PhaseEnum phase){
        phaseCon.setPhase(phase);
    }

    void setMessage(NotificationEnum message) {
        notiCon.setMessage(message);
    }

    CameraController getCameraCon() {
        return mapCon.getCameraCon();
    }

    public Translate getPlayerPos(PlayerController player){
        return player.get3dPos();
    }


}
