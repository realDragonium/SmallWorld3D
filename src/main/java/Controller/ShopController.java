package Controller;

import Firebase.FirebaseGameObserver;
import Model.ShopModel;
import Observer.ShopObserver;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.transform.Translate;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShopController implements FirebaseGameObserver {

    private GameController gameCon;
    private ShopModel model = new ShopModel();
    private FirebaseGameController fbGame;

    ShopController(GameController gameCon) {
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
        fbGame.register("add", this::addUpdate);
        fbGame.register("buy", this::buyUpdate);
    }

    private void buyingItem(int item) {
        if (model.getShopItems().size() > item) {
            gameCon.getCurrentPlayer().buyFromShop(model.getShopItem(item), item);
//            gameCon.getGameTurn().endTurn();
        }
    }

    public void buyToFirebase(int item){
        if (model.getShopItems().size() > item) {
            fbGame.buyCombiAction(item);
            TimerTask start = new TimerTask() {
                @Override
                public void run() {
                    createRandomShopItem();
                }
            };
            new Timer().schedule(start, 1000);
        }
    }

    public void setShopPosition(double xPos, double yPos) {
        model.setShopPosition(new Translate(xPos, yPos));
        setShopItemPositions();
    }

    private void setShopItemPositions() {

        for (int i = 0; i < 6; i++) {
            Translate pos = new Translate(model.getPosition().getX(), model.getPosition().getY() + i * 100);
            model.addItemPosition(pos);
        }
    }

    public void registerObserver(ShopObserver obs) {
        model.register(obs);
    }

    void createRandomShopItem() {
        String race = model.getRandomRace();
        String power = model.getRandomPower();
        fbGame.addCombiAction(race, power);
    }

    private void createSpecificShopItems(String race, String power) {
        model.addShopItem(new CombinationController(race, power));
    }

    public List<CombinationController> getShopItems() {
        return model.getShopItems();
    }

    @Override
    public void update(DocumentSnapshot ds) {
        System.out.println("[SHOPCON UPDATE] Als je deze iets, dan wordt deze nog gebruikt helaas. ");
    }

    private void addUpdate(DocumentSnapshot ds) {
        String race = ds.getString("race");
        String power = ds.getString("power");
        createSpecificShopItems(race, power);
    }

    private void buyUpdate(DocumentSnapshot ds) {
        buyingItem(ds.getDouble("item").intValue());
    }

    public void createCombinationView(int index, Group group) {
        gameCon.createCombinationView(group, model.getShopItems().get(index));
    }
}
