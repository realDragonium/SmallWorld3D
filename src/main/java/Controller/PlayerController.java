package Controller;

import Firebase.FirebaseGameObserver;
import Model.PlayerModel;
import Objects.SpecialFXMLLoader;
import Observer.PlayerObserver;
import View.PlayerView;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.transform.Translate;

import java.util.List;
import java.util.concurrent.Callable;

public class PlayerController implements FirebaseGameObserver {
    private GameController gameCon;
    private PlayerModel model;


    public PlayerController(int playerID, GameController gameCon) {
        model = new PlayerModel(playerID);
        this.gameCon = gameCon;
        createPlayerView();
    }

    public PlayerController(String name) {
        model = new PlayerModel(name);
    }

    private void createPlayerView() {
        new SpecialFXMLLoader().loader("/PlayerView.fxml", (Callable<PlayerView>) () -> new PlayerView(getName(), this));
    }

    public void setPlayer3dPosition(Translate pos) {
        model.setPlayer3dPos(pos);
    }

    public Translate get3dPos() {
        return model.get3dPos();
    }

    void buyFromShop(CombinationController combo, int costs) {
        model.removePoints(costs);
        model.addCombi(combo);
        gameCon.getTurnCon().setCurrentCombi(combo);
        combo.setPlayer(this);
        combo.createRaceFiches();
        combo.setPowersActive();
        model.notifyObserver();
    }

    void setDeclineCombi(CombinationController combi) {
        for (int i = 0; i < model.getDeclineCombies().size(); i++) {
            CombinationController combo = model.getDeclineCombies().get(i);
            if (!combo.isActive())
                removecombi(combo);
        }
        model.declineCombi(combi);
        model.notifyObserver();
    }

    void removecombi(CombinationController combi) {
        combi.selfDestruct();
        model.removeCombi(combi);
    }

    List<CombinationController> getCombinations() {
        return model.getCombies();
    }

    List<CombinationController> getDeclineCombinations() {
        return model.getDeclineCombies();
    }

    public int getId() {
        return model.getId();
    }

    public void register(PlayerObserver po) {
        model.register(po);
    }

    public CombinationController getCurrentCombi() {
        return model.getCurrentCombi();
    }

    @Override
    public void update(DocumentSnapshot ds) {
        model.points = ds.getDouble("points").intValue();
        model.notifyObserver();
    }

    public GameController getGameCon() {
        return gameCon;
    }

    public void addPoints(int i) {
        model.addPunten(i);
    }

    int getPoints() {
        return model.points;
    }

    void setPlayer2dPosition(Translate position) {
        model.setPlayer2dPos(position);
    }

    public String getName() {
        return model.getName();
    }

    public void fichesChanged(){
        model.notifyObserver();
    }

    public void setPlayerName(String name) {
        model.setName(name);
    }
}
