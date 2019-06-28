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
//    private Applicatie app = SceneManager.getInstance().getApp();
//    private FirebaseServiceOwn fb = app.getFirebaseService();
    private PlayerModel model;
    private List<CombinationController> combinations = new ArrayList<>();

    public PlayerController(String playerID, GameController gameCon) {
        model = new PlayerModel(playerID);
        this.gameCon = gameCon;
//        SceneManager.getInstance().loadPlayer(playerID, this);
//        fb.playerListen(playerID, this);
    }

    public void setPlayerPosition(Translate pos){
        model.setPlayerPos(pos);
    }

    void buyFromShop(CombinationController combo, int costs) {
        model.removePoints(costs);
        combinations.add(combo);
        combo.setPlayer(this);
        Map<String, Object> info = new HashMap<>();
        info.put("fiches", model.getRaceFichesAmount());
        info.put("points", model.getPoints());
//        fb.playerUpdate(gameCon.getRace().getId(), info);
    }

    void showActiveCombiFichesLeft() {
        for (CombinationController combiCon : combinations) {
            combiCon.getRace().fichesOver();
        }
    }

    CombinationController getActiveCombination() {
        if (combinations.size() > 0) return combinations.get(0);
        return null;
    }

    public String getId() {
        return model.getId();
    }

    public void register(PlayerObserver po) {
        model.register(po);
    }

    public void addRaceFiche(FicheController fiche) {
        model.addRaceFiche(fiche);
//        fb.playerUpdateFiches(model.getId(), fiches);
    }

    public FicheController removeRaceFiche(){
        return model.removeRaceFiche();
    }

    private boolean hasCombination(){
        return (combinations.size() > 0);
    }

    boolean hasActiveCombination(){
        if(hasCombination()) {
            return combinations.get(0).isActive();
        }
        return false;
    }


    @Override
    public void update(DocumentSnapshot ds) {
//        if(gameCon.getCurrentPlayer()==this) return;
//        model.fiches = (int) Math.round(ds.getDouble("fiches"));
        model.points = (int) Math.round(ds.getDouble("points"));
        model.notifyObserver();
    }

    void addRoundPoints() {
        if(hasCombination()){
            for(CombinationController combi : combinations)
            model.addPunten(combi.getRace().getAreasAmount());
        }
    }

    public GameController getGameCon() {
        return gameCon;
    }

    public void addPoints(int i) {
        model.addPunten(i);
//        fb.changePointsPlayer(model.getId(), model.getPoints());
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
}
