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


    public PlayerController(int playerID, GameController gameCon, String name) {
        model = new PlayerModel(playerID, name);
        this.gameCon = gameCon;
        createPlayerView();
    }

    public PlayerController(String name){
        model = new PlayerModel(name);
    }

    private void createPlayerView(){
        new SpecialFXMLLoader().loader("/PlayerView.fxml", (Callable<PlayerView>)() -> new PlayerView(getName(), this));
    }

    public void setPlayer3dPosition(Translate pos){
        model.setPlayer3dPos(pos);
    }

    public Translate get3dPos(){
        return model.get3dPos();
    }

    void buyFromShop(CombinationController combo, int costs) {
        model.removePoints(costs);
        model.addCombi(combo);
//        combo.moveToPosition(model.get2dPos());
        combo.setPlayer(this);
        combo.createRaceFiches();
        combo.setPowersActive();
    }

    void setDeclineCombi(CombinationController combi){
        model.declineCombi(combi);
    }

    void removecombi(CombinationController combi){
        model.removeCombi(combi);
    }

    List<CombinationController> getCombinations(){
        return model.getCombies();
    }

    public int getId() {
        return model.getId();
    }

    public void register(PlayerObserver po) {
        model.register(po);
    }

    public CombinationController getCurrentCombi(){
        return model.getCurrenCombi();
    }

    @Override
    public void update(DocumentSnapshot ds) {
        model.points = (int) Math.round(ds.getDouble("points"));
        model.notifyObserver();
    }

    public GameController getGameCon() {
        return gameCon;
    }

    public void addPoints(int i) {
        model.addPunten(i);
    }

    int getPoints(){
        return model.points;
    }

    public void setPlayer2dPosition(Translate position) {
        model.setPlayer2dPos(position);
    }

    public String getName() {
        return model.getName();
    }
}
