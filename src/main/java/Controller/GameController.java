package Controller;

import Model.GameModel;
import Objects.FXMLLOADER;
import Observer.GameObserver;
import View.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import Enum.*;
import java.util.ArrayList;
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
    Translate playerPos2d = new Translate(600, 900);

    Translate player1Pos = new Translate(0, 0, -600);
    Translate player2Pos = new Translate(-600, 0, 0);
    Translate player3Pos = new Translate(0, 0, 600);
    Translate player4Pos = new Translate(600, 0, 0);

    private Controller3D con3d;
    private ApplicationController appCon;
    private FXMLLOADER fxmlLoader = new FXMLLOADER();
    private MapController mapCon;
    private ButtonController buttonCon;
    private TimerController timerCon;
    private VervallenController vervalCon;
    private InfoController infoCon;
    private NotificationController notiCon;


    private GameModel model;
    private Map<String, PlayerController> players = new HashMap<>();
    private PlayerController currentPlayer;
    private RoundController roundCon;
    private TurnController turnCon;
    private AreaInformationController areaInfoCon;
    private CameraController cameraCon;

    private String lobbyName;
    private VervallenController vervCon;
    private TimerController timeCon;
    private GameTimer gameTimer;
    private ShopController shopCon;
    private PhaseController phaseCon;
    private RedeployingController redCon;
    private DiceController diceCon;

    public GameController(ApplicationController appCon) {
        this.appCon = appCon;
        int numberOfPlayers = 4;
        model = new GameModel(8, createPlayers(numberOfPlayers));

        createControllers();
    }

    private List<PlayerController> createPlayers(int numberOfPlayers){
        List<PlayerController> players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++)
            players.add(new PlayerController("player"+i, this));

        return players;
    }


    public void startGame(){
        turnCon.nextTurn();
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
//        players.get("player1").addRaceFiche(con3d.createRaceFiche("ratten"));
//        players.get("player1").addRaceFiche(con3d.createRaceFiche("ratten"));
    }

    public void create3dView(Group group){
        new Map3DView(mapCon, group);
    }

    public void createMap2DView(Group group){
        fxmlLoader.loader("/Map/UglyMap5.fxml", (Callable<Map2DView>)() -> new Map2DView(mapCon, group));
    }

    public void createPlayerView(Group group, int id){
        PlayerController player = model.getPlayer(id);
        fxmlLoader.loader("/PlayerView.fxml", (Callable<PlayerView>)() -> new PlayerView(id, group, player));
    }

    public void createUIOverlay(Group group) {
        System.out.println("creating ui");
        fxmlLoader.loader("/UI/UIView.fxml", (Callable<UIView>)() -> new UIView(group));
    }

    public void createRoundView(Group group) {
        fxmlLoader.loader("/RoundView.fxml", (Callable<RoundView>)() -> new RoundView(group, roundCon));
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

    public Crystal createCrystal(){
        Crystal crystal = new Crystal(addable3d);
        return crystal;
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
        fxmlLoader.loader("/RedeployingView.fxml", (Callable<RedeployingView>) () -> new RedeployingView(group, redCon));
    }

    public void createInfoView(Group group) {
        fxmlLoader.loader("/InfoScreen/InfoView.fxml", (Callable<InfoView>)() -> new InfoView(group, infoCon));
    }

    public void createPhaseView(Group group) {
        fxmlLoader.loader("/PhaseView.fxml", (Callable<PhaseView>) () -> new PhaseView(group, phaseCon));
    }

    public void createCombinationView(Group group, CombinationController combiCon) {
        fxmlLoader.loader("/CombinationView.fxml", (Callable<CombinationView>)() -> new CombinationView(group, combiCon));
    }

    public void createNotifiView(Group group) {
        fxmlLoader.loader("/NotificationView.fxml", (Callable<NotificationView>) () -> new NotificationView(group, notiCon));
    }

    public void createRaceFiche(FicheController con){
        new fiche3dView(con, addable3d);
    }

    public CameraView createCamera(Group camera) {
        cameraCon = new CameraController();
        return new CameraView(cameraCon, camera);
    }


    private void createControllers() {
        fbGame = new FirebaseGameController("test1", this);
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
        notiCon = new NotificationController(this);
    }


    public PlayerController getPlayer(int id){
        return model.getPlayer(id);
    }

    public PlayerController getCurrentPlayer(){
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

    public VervallenController getVervCon(){
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

    public void nextTurn() {
        turnCon.nextTurn();
        System.out.println("next Turn!");
    }

    GameTimer getGameTimer() {
        return gameTimer;
    }

    TimerController getTimer(){
        return timeCon;
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

    public List<PlayerController> getPlayers() {
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


    public CameraController getCameraCon() {
        return cameraCon;
    }

    public Translate getPlayerPos(PlayerController player){
        return player.get3dPos();
    }
}
