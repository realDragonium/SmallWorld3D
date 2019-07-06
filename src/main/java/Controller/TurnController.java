package Controller;

import Enums.GameViewEnum;
import Firebase.FirebaseGameObserver;
import Model.TurnModel;
import Objects.SpecialFXMLLoader;
import Observer.TurnObserver;
import Turn.*;
import View.TurnView;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.transform.Translate;

import java.util.LinkedList;
import java.util.concurrent.Callable;


public class TurnController implements FirebaseGameObserver {

    private TurnModel model;
    private GameController gameCon;
    private PhaseController phaseCon;
    private RoundController roundCon;


    TurnController(GameController gameCon){
        model = new TurnModel(gameCon.getPlayers(), gameCon.imPlayer());
        this.gameCon = gameCon;
        createTurnView();
        manageControllers();
        registerFirebase();
        newRound();
        model.currentPlayer = gameCon.getPlayer(model.myPlayerId);
        rotateCamera();
    }

    private void manageControllers(){
        this.phaseCon = gameCon.getPhaseCon();
        this.roundCon = gameCon.getRoundCon();
        phaseCon.setTurnCon(this);
    }

    private void registerFirebase(){
        FirebaseGameController fbGame = gameCon.getFireBase();
        fbGame.register("attack", this::attackUpdate);
        fbGame.register("decline", this);
        fbGame.register("addFiche", this::addFicheUpdate);
        fbGame.register("removeFiche", this::removeFicheUpdate);
        fbGame.register("leaves", this::leaveAreaUpdate);
        fbGame.register("turn", this::turnUpdate);
        fbGame.register("dice", this::diceUpdate);
        fbGame.register("specificattack", this::specificAttackUpdate);
    }

    private void createTurnView() {
        new SpecialFXMLLoader().loader("/TurnView.fxml", (Callable<TurnView>) () -> new TurnView(this));
    }

    public void register(TurnObserver to){
        model.register(to);
    }

    void newRound(){
        LinkedList<Turn> turns = new LinkedList<>();
        int imPlayer = model.myPlayerId;
        for(PlayerController player : gameCon.getPlayers()){
            if(player.getId() != imPlayer)
                fixTurnOtherPlayer(turns, player);
            else
                fixMyOwnTurns(turns, player);
        }
        model.turns = turns;
    }

    private void fixTurnOtherPlayer(LinkedList<Turn> turns, PlayerController player){
        for(CombinationController combi: player.getDeclineCombinations()){
            turns.add(new NotMyTurn(player, combi));
        }
        if(player.getCurrentCombi() == null)
            turns.add(new NotMyTurn(player, null));
         else
            turns.add(new NotMyTurn(player, player.getCurrentCombi()));


    }

    private void fixMyOwnTurns(LinkedList<Turn> turns, PlayerController player){
        for(CombinationController combi: player.getDeclineCombinations()){
            turns.add(new MyTurn(player, combi));
        }
        if(player.getCurrentCombi() == null)
            turns.add(new ShopTurn(player, null));
        else
            turns.add(new MyTurn(player, player.getCurrentCombi()));
    }

    void nextTurn(){
        if(model.turns.size() == 0) roundCon.nextRound();
        model.currentTurn = model.turns.pop();
        model.currentCombi = model.currentTurn.getCombi();
        model.currentPlayer = model.currentTurn.getPlayer();
        model.currentTurn.nextTurn(phaseCon);
        rotateCamera();
        model.notifyObservers();
    }

    PlayerController getCurrentPlayer() {
        return model.currentPlayer;
    }

    CombinationController getCurrentCombi(){
        return model.currentCombi;
    }

    void setCurrentCombi(CombinationController combi){
        model.currentCombi = combi;
        model.notifyObservers();
    }

    private void rotateCamera(){
        PlayerController player = model.currentPlayer;
        Translate cameraPos = new Translate(player.get3dPos().getX(), -800, player.get3dPos().getZ());
        gameCon.getCameraCon().moveToPosition(cameraPos, 5);
        int rotationY = 0;
        if(player.get3dPos().getX() == -600){
            rotationY = 90;
        }
        else if(player.get3dPos().getZ() == 600){
            rotationY = 180;
        }

        else if(player.get3dPos().getX() == 600){
            rotationY = 270;
        }

        gameCon.getCameraCon().rotateToAngle(-60, rotationY , 5);
    }

    private void reloadAreaInfoViews(){
        phaseCon.changeView();
    }

    @Override
    public void update(DocumentSnapshot ds) {
        gameCon.removeFromGameView(GameViewEnum.DECLINE);
        if(ds.getString("action").equals("decline")) {
            getCurrentCombi().goIntoDecline();
            phaseCon.nextTurn();
        } else {
            getCurrentCombi().checkPrepareAreas();
            getCurrentCombi().prepareRound();
        }
    }

    private void attackUpdate(DocumentSnapshot ds){
        AreaController area = getArea(ds.getString("areaId"));
        getCurrentCombi().attackThisArea(area);
        reloadAreaInfoViews();
    }

    private void diceUpdate(DocumentSnapshot ds){
        gameCon.getDiceCon().update(ds);
    }

    private void specificAttackUpdate(DocumentSnapshot ds){
        AreaController area = getArea(ds.getString("areaId"));
        getCurrentCombi().attack(area, ds.getDouble("number").intValue());
        reloadAreaInfoViews();
    }

    private void addFicheUpdate(DocumentSnapshot ds){
        AreaController area = getArea(ds.getString("areaId"));
        area.addFiche();
        reloadAreaInfoViews();
    }

    private void removeFicheUpdate(DocumentSnapshot ds){
        AreaController area = getArea(ds.getString("areaId"));
        area.returnFiche();
        reloadAreaInfoViews();
    }

    private void leaveAreaUpdate(DocumentSnapshot ds){
        AreaController area = getArea(ds.getString("areaId"));
        getCurrentCombi().leaveArea(area);
        reloadAreaInfoViews();
    }

    private void turnUpdate(DocumentSnapshot ds){
        nextTurn();
    }

    private AreaController getArea(String id){
        return gameCon.getMapCon().getAreaCon(id);
    }

}