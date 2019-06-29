package Controller;

import Firebase.FirebaseGameObserver;
import Model.PlayerModel;
import Objects.ShopCombination;
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
    private List<CombinationController> combinations = new ArrayList<>();

    public PlayerController(String playerID, GameController gameCon) {
        model = new PlayerModel(playerID);
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
    }

    public void setPlayerPosition(Translate pos){
        model.setPlayerPos(pos);
    }

    void buyFromShop(ShopCombination combo, int costs) {
        model.removePoints(costs);
        CombinationController combi = new CombinationController(combo.getRace(), combo.getPower());
        combinations.add(combi);
        combi.setPlayer(this);
        Map<String, Object> info = new HashMap<>();
        info.put("fiches", model.getRaceFichesAmount());
        info.put("points", model.getPoints());
    }

    public void buyFromShop(ShopController shop, int number){
//        combinations.add(shop.buyItem(number));
        model.removePoints(number);
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
