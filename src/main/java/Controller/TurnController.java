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
        this.phaseCon = gameCon.getPhaseCon();
        this.roundCon = gameCon.getRoundCon();
        model.newRound();
        model.currentPlayer = gameCon.getPlayer(0);
        createTurnView();
        registerFirebase();
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
        model.getTurns().pop().nextTurn(phaseCon);
        model.currentPlayer = model.players.get(3 - model.getTurns().size());
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
        model.notifyObservers();
    }

    PlayerController getCurrentPlayer() {
        return model.currentPlayer;
    }

    @Override
    public void update(DocumentSnapshot ds) {
        //Decline updates
        getCurrentPlayer().getCurrentCombi().goIntoDecline();
    }

    private void attackUpdate(DocumentSnapshot ds){
        AreaController area = gameCon.getMapCon().getAreaCon(ds.getString("areaId"));
        getCurrentPlayer().getActiveCombination().attackThisArea(area);
    }

    public void addFicheUpdate(DocumentSnapshot ds){
        AreaController area = gameCon.getMapCon().getAreaCon(ds.getString("areaId"));
        area.addFiche();
    }

    public void removeFicheUpdate(DocumentSnapshot ds){
        AreaController area = gameCon.getMapCon().getAreaCon(ds.getString("areaId"));
        area.removeFiche();
    }

    public void leaveAreaUpdate(DocumentSnapshot ds){
        AreaController area = gameCon.getMapCon().getAreaCon(ds.getString("areaId"));
        area.leaveArea();
    }

}