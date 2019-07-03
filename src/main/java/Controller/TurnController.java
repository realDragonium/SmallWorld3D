package Controller;

import Firebase.FirebaseGameObserver;
import Model.TurnModel;
import Objects.SpecialFXMLLoader;
import Observer.TurnObserver;
import View.TurnView;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.transform.Translate;

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
        model.newRound();
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
    }

    private void createTurnView() {
        new SpecialFXMLLoader().loader("/TurnView.fxml", (Callable<TurnView>) () -> new TurnView(this));
    }

    public void register(TurnObserver to){
        model.register(to);
    }

    void newRound(){
        model.newRound();
    }

    void nextTurn(){
        if(model.getTurns().size() == 0) roundCon.nextRound();
        model.currentPlayer = model.players.get(4 - model.getTurns().size());
        model.getTurns().pop().nextTurn(phaseCon);
        rotateCamera();
        model.notifyObservers();
    }

    PlayerController getCurrentPlayer() {
        return model.currentPlayer;
    }

    private void rotateCamera(){
        PlayerController player = model.currentPlayer;
        Translate cameraPos = new Translate(player.get3dPos().getX(), -700, player.get3dPos().getZ());
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
        System.out.println("change View");
    }

    @Override
    public void update(DocumentSnapshot ds) {
        //Decline updates
        getCurrentPlayer().getCurrentCombi().goIntoDecline();
    }

    private void attackUpdate(DocumentSnapshot ds){
        AreaController area = getArea(ds.getString("areaId"));
        getCurrentPlayer().getCurrentCombi().attackThisArea(area);
        reloadAreaInfoViews();
    }

    public void addFicheUpdate(DocumentSnapshot ds){
        AreaController area = getArea(ds.getString("areaId"));
        area.addFiche();
        reloadAreaInfoViews();
    }

    public void removeFicheUpdate(DocumentSnapshot ds){
        AreaController area = getArea(ds.getString("areaId"));
        area.removeFiche();
        reloadAreaInfoViews();
    }

    public void leaveAreaUpdate(DocumentSnapshot ds){
        AreaController area =  getArea(ds.getString("areaId"));
        getCurrentPlayer().getCurrentCombi().leaveArea(area);
        reloadAreaInfoViews();
    }

    private AreaController getArea(String id){
        return gameCon.getMapCon().getAreaCon(id);
    }

}