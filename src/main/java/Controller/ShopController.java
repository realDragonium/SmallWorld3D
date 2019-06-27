package Controller;

import Firebase.FirebaseGameObserver;
import Model.ShopModel;
import Objects.*;
import Observer.ShopObserver;
import Objects.PowerOld;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class ShopController implements FirebaseGameObserver {

    GameController gameCon;
    ShopModel model = new ShopModel();
    private List<RaceController> races = new ArrayList<>();
    private List<PowerOld> powerOlds = new ArrayList<>();

    ShopController(GameController gameCon) {
        this.gameCon = gameCon;
//        SceneManager.getInstance().loadShop(this);
//        SceneManager.getInstance().getApp().getFirebaseService().shopListener(this);
    }

    public void makeItems(){
        createShopItems();
        for (int i = 0; i < 6; i++) {
            makeNewCombination();
        }
    }

    private void removeItem(double item){
        model.removeItem((int)item);
    }

    public void buyingItem(int item) {
        if (model.getShopItems().size() > item) {
            gameCon.getCurrentPlayer().buyFromShop(model.getShopItems().get(item), item);
//            SceneManager.getInstance().getApp().getFirebaseService().boughShop(item);
//            gameCon.getGameTurn().endTurn();
        }
    }

    public void registerObserver(ShopObserver obs) {
        model.register(obs);
    }

    private void createShopItems() {
        races.add(new RaceController(new RattenKracht(), "rats", 12));
        races.add(new RaceController(new WizzardsKracht(), "wizzards", 8));
        races.add(new RaceController(new DwarvesKracht(), "dwarves", 7));
        races.add(new RaceController(new TritansKracht(), "tritans", 10));
        races.add(new RaceController(new HumanKracht(), "humans", 9));

        powerOlds.add(new AlchemistPowerOld());
        powerOlds.add(new WelthPowerOld());
        powerOlds.add(new AlchemistPowerOld());
        powerOlds.add(new WelthPowerOld());
        powerOlds.add(new AlchemistPowerOld());
    }

    private void makeNewCombination() {
        RaceController race = getRandomRace();
        PowerOld powerOld = getRandomPower();
        if (race != null && powerOld != null) {
            CombinationController combination = new CombinationController(race, powerOld);
            race.setCombiCon(combination);
            model.addShopItem(combination);
        }
    }

    private RaceController getRandomRace() {
        if (races.size() != 0) {
            return races.remove(0);
        }
        return null;
    }

    private PowerOld getRandomPower() {
        if (powerOlds.size() != 0) {
            return powerOlds.remove(0);
        }
        return null;
    }

    public List<CombinationController> getShopItems(){
        return model.getShopItems();
    }

    @Override
    public void update(DocumentSnapshot ds) {
        if(ds.get("bought")==null) return;
        Platform.runLater(()-> removeItem(ds.getDouble("bought")));
    }
}
