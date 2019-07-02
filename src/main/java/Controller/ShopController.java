package Controller;

import Firebase.FirebaseGameObserver;
import Managers.AnimationsManager;
import Model.ShopModel;
import Objects.Animatable;
import Objects.AnimationPoint;
import Objects.SpecialFXMLLoader;
import Observer.ShopObserver;
import View.ShopView;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class ShopController implements FirebaseGameObserver, Animatable {

    private GameController gameCon;
    private ShopModel model = new ShopModel();
    private FirebaseGameController fbGame;

    ShopController(GameController gameCon) {
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
        fbGame.register("add", this::addUpdate);
        fbGame.register("buy", this::buyUpdate);
        createShopView();
    }

    private void createShopView() {
        new SpecialFXMLLoader().loader("/ShopView.fxml", (Callable<ShopView>) () -> new ShopView(this));
    }

    private void buyingItem(int item) {
        if (model.getShopItems().size() > item) {
            gameCon.getCurrentPlayer().buyFromShop(model.getShopItem(item), item);
        }

    }

    public void buyToFirebase(int item){
        if (model.getShopItems().size() > item) {
            fbGame.buyCombiAction(item);
            fbGame.nextPhaseAction();
            TimerTask start = new TimerTask() {
                @Override
                public void run() {
                    createRandomShopItem();
                }
            };
            new Timer().schedule(start, 1000);
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
        model.removePower(power);
        model.removeRace(race);
        model.addShopItem(new CombinationController(gameCon, race, power));
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

    public int getShopItem(CombinationController item) {
        return model.getShopItemIndex(item);
    }

    public void moveToPosition(Translate position){
        AnimationsManager.getInstance().createMoveToAnimation(this, position, 2);
    }

    public void hideShop() {
        moveToPosition(new Translate(1920, model.getPosition().getY()));
    }

    public void showShop(){
        moveToPosition(new Translate(1570, model.getPosition().getY()));
    }

    @Override
    public void resetToOrigin(AnimationPoint animPoint) {
        System.out.println("reset");
    }

    @Override
    public void doAnimation(AnimationPoint animPoint) {
        model.moveToPosition(animPoint.getTranslate());
    }

    @Override
    public Translate getCurrentPosition() {
        return model.getPosition();
    }

    @Override
    public double getCurrentXAngle() {
        return 0;
    }

    @Override
    public double getCurrentYAngle() {
        return 0;
    }

    public void setShopPosition(double xPos, double yPos) {
        model.setShopPosition(new Translate(xPos, yPos));

    }
}
