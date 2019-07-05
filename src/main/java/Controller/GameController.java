package Controller;

import Firebase.FirebaseGameObserver;
import Model.GameModel;
import Objects.SpecialFXMLLoader;
import Observer.GameObserver;
import View.*;
import Enums.*;

import java.util.*;
import java.util.concurrent.Callable;

import Enums.GameViewEnum;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.transform.Translate;

public class GameController implements FirebaseGameObserver {

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
    private VoteController currentVote;
    private InfoController infoCon;
    private NotificationController notiCon;
    private LeaveController leaveCon;
    private CombinationInfoController combiInfoCon;


    private GameModel model;
    private Map<String, PlayerController> players = new HashMap<>();
    private PlayerController currentPlayer;
    private RoundController roundCon;
    private TurnController turnCon;
    private AreaInformationController areaInfoCon;

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
        setPlayerPositions();
        createControllers();
        fbGame.register("start", this);
    }

    private List<PlayerController> createPlayers(int numberOfPlayers){
        List<PlayerController> players = new ArrayList<>();

        players.add(new PlayerController(0, this, "Beau1"));
        players.add(new PlayerController(1, this, "Beau2"));
        players.add(new PlayerController(2, this, "Beau3"));
        players.add(new PlayerController(3, this, "Beau4"));

//        for(int i = 0; i < numberOfPlayers; i++)
//            players.add(new PlayerController(i, this));

        return players;
    }


    public void startGame(){
        turnCon.nextTurn();
    }

    public void startFbListener(){
        fbGame.activateListener();
//        createShopItems();
    }

    private void createShopItems(){
        for (int i = 0; i < 6; i++) {
            shopCon.createRandomShopItem();
        }
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



    public void createUIOverlay() {
        new SpecialFXMLLoader().loader("/UIBottom.fxml", (Callable<UIBottom>) () -> new UIBottom(GameViewEnum.UIOVERLAY.getGroup(), this));
        new SpecialFXMLLoader().loader("/UI/UIView.fxml", (Callable<UIView>) () -> new UIView(GameViewEnum.UIOVERLAY.getGroup()));
        new SpecialFXMLLoader().loader("/UIPlayers.fxml", (Callable<UIPlayer>) () -> new UIPlayer(GameViewEnum.UIOVERLAY.getGroup()));
    }
    //OPSTART PROCEDURE
    private void createControllers() {
        fbGame = new FirebaseGameController("test", this);
        new Thread(fbGame).start();
        //Belangrijk
        mapCon = new MapController(this);
        shopCon = new ShopController(this);

        phaseCon = new PhaseController(this);
        roundCon = new RoundController(this);
        turnCon = new TurnController(this);
        timerCon = new TimerController(this);

        declineCon = new DeclineController(this);
        buttonCon = new ButtonController(this);
        notiCon = new NotificationController(this);
        redCon = new RedeployingController(this);
        attCon = new AttackController(this);
        infoCon = new InfoController(this);
        diceCon = new DiceController(this);
        combiInfoCon = new CombinationInfoController(this);
        areaInfoCon = new AreaInformationController(this);
        leaveCon = new LeaveController(this);
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

    public TurnController getTurnCon(){
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

    GameTimer getGameTimer() {
        return gameTimer;
    }

    TimerController getTimer(){
        return timeCon;
    }

    void addToGameView(GameViewEnum go){
        if(!model.getCurrenViews().contains(go)) model.addActiveView(go);
    }

    void removeFromGameView(GameViewEnum go){
        model.removeActiveView(go);
    }

    void changeGameView(List<GameViewEnum> views){
        model.changeGameView(views);
    }

    AreaInformationController getAreaInfoCon() {
        return areaInfoCon;
    }

    public AttackController getAttCon(){
        return attCon;
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

    public int imPlayer(){
        return model.imPlayer();
    }

    public void setPhase(PhaseEnum phase){
        phaseCon.setPhase(phase);
    }

    public void setMessage(NotificationEnum message) {
        notiCon.setMessage(message);
    }

    CameraController getCameraCon() {
        return mapCon.getCameraCon();
    }

    public Translate getPlayerPos(PlayerController player){
        return player.get3dPos();
    }

    public void setAreaInfoViewButtonInArea(AreaInfoEnum areainfo, String areaId){
        mapCon.getAreaCon(areaId).setAreaInfoButton(areainfo);

    }

    public void createVote(int i, String message) {
        currentVote = new VoteController(i, message, this);
    }

    public void setTimer(int time, boolean b) {
        timerCon.restartTimer(time, b);
    }

    public int rollDice(){
        return diceCon.rollDice();
    }

    public void showCombinationInfo(CombinationController combi) {
        combiInfoCon.showCombinationInfo(combi);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        startGame();
    }
}
