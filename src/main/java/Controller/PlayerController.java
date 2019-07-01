package Controller;

import Firebase.FirebaseGameObserver;
import Model.PlayerModel;
import Observer.PlayerObserver;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerController implements FirebaseGameObserver {
    private GameController gameCon;
    private FirebaseGameController fbGame;
    private PlayerModel model;


    public PlayerController(String playerID, GameController gameCon) {
        model = new PlayerModel(playerID);
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
    }

    public void setPlayer3dPosition(Translate pos){
        model.setPlayer3dPos(pos);
    }

    public Translate get3dPos(){
        return model.get3dPos();
    }

    void buyFromShop(CombinationController combo, int costs) {
        model.removePoints(costs);
        model.getCombies().add(combo);
        combo.moveToPosition(model.get2dPos());
        combo.setPlayer(this);
        combo.createRaceFiches();
    }

//    void showActiveCombiFichesLeft() {
//        for (CombinationController combiCon : combinations) {
//            combiCon.getRace().fichesOver();
//        }
//    }

    CombinationController getActiveCombination() {
        if (model.getCombies().size() > 0) return model.getCombies().get(0);
        return null;
    }

    public String getId() {
        return model.getId();
    }

    public void register(PlayerObserver po) {
        model.register(po);
    }

    private boolean hasCombination(){
        return (model.getCombies().size() > 0);
    }

    boolean hasActiveCombination(){
        if(hasCombination()) {
            return model.getCombies().get(0).isActive();
        }
        return false;
    }


    public CombinationController getCurrentCombi(){
        return model.getCurrenCombi();
    }

    @Override
    public void update(DocumentSnapshot ds) {
        model.points = (int) Math.round(ds.getDouble("points"));
        model.notifyObserver();
    }

//    void addRoundPoints() {
//        if(hasCombination()){
//            for(CombinationController combi : combinations)
//            model.addPunten(combi.getRace().getAreasAmount());
//        }
//    }

    public GameController getGameCon() {
        return gameCon;
    }

    public void addPoints(int i) {
        model.addPunten(i);
    }

    int getPoints(){
        return model.points;
    }

    boolean isConnected(){
        return model.connected;
    }

    public void disconnect(){
        model.connected = false;
    }

    public void connect(){
        model.connected = true;
    }


    public boolean hasEnoughFiches(int fichesNeeded) {
        System.out.println(model.getRaceFichesAmount());
        return model.getRaceFichesAmount() >= fichesNeeded;
    }

    public void setPlayer2dPosition(Translate position) {
        model.setPlayer2dPos(position);
    }
}
