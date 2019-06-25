package Controller;

import Applicatie.Applicatie;
import Firebase.FirebaseControllerObserver;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.PlayerModel;
import Observer.PlayerObserver;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerController implements FirebaseControllerObserver {
    private GameController gameCon;
    private Applicatie app = SceneManager.getInstance().getApp();
    private FirebaseServiceOwn fb = app.getFirebaseService();
    private PlayerModel model;
    private List<CombinationController> combinations = new ArrayList<>();

    public PlayerController(String playerID, GameController gameCon) {
        model = new PlayerModel(playerID);
        this.gameCon = gameCon;
        SceneManager.getInstance().loadPlayer(playerID, this);
        fb.playerListen(playerID, this);
    }

    void buyFromShop(CombinationController combo, int costs) {
        System.out.println(getId() + " voegt combinatie toe");
        model.removePoints(costs);
        combinations.add(combo);
        combo.setPlayer(this);
        setFiches(combo.getRace().fichesCount());
        Map<String, Object> info = new HashMap<>();
        info.put("fiches", model.getFiches());
        info.put("punten", model.getPunten());
        fb.playerUpdate(gameCon.getPlayer().getId(), info);
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

    String getId() {
        return model.getId();
    }

    public void register(PlayerObserver po) {
        model.register(po);
    }

    public void setFiches(int fiches) {
        model.fiches = fiches;
        model.notifyObserver();
        fb.playerUpdateFiches(model.getId(), fiches);
    }

    private boolean hasCombination(){
        return (combinations.size() > 0);
    }

    boolean hasActiveCombination(){
        if(hasCombination()) {
            if(combinations.get(0).isActive()){
                return true;
            }
        }
        return false;
    }

    void lowerFiches(int count) {
        model.fiches -= count;
        model.notifyObserver();
        fb.playerUpdateFiches(model.getId(), model.fiches);
    }

    void higherFiches(int count) {
        model.fiches += count;
        model.notifyObserver();
        fb.playerUpdateFiches(model.getId(), model.fiches);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        System.out.println(ds.getData());
//        if(gameCon.getCurrentPlayer()==this) return;
        model.fiches = (int) Math.round(ds.getDouble("fiches"));
        model.punten = (int) Math.round(ds.getDouble("punten"));
        model.notifyObserver();
    }

    void returnFiches() {
        getActiveCombination().returnFiches();
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
        fb.changePointsPlayer(model.getId(), model.getPunten());
    }
}
