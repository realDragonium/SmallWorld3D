package Controller;

import Firebase.FirebaseGameObserver;
import Model.ShopModel;
import Observer.ShopObserver;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.Group;
import javafx.scene.transform.Translate;

import java.util.List;

public class ShopController implements FirebaseGameObserver {

    private GameController gameCon;
    private ShopModel model = new ShopModel();
    private FirebaseGameController fbGame;

    ShopController(GameController gameCon) {
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
        fbGame.register("shop", this);
    }

    public void makeItems(){

    }

    private void removeItem(double item){
        model.removeItem((int)item);
    }

    public void buyingItem(int item) {
        if (model.getShopItems().size() > item) {
            gameCon.getCurrentPlayer().buyFromShop(model.getShopItem(item), item);
//            gameCon.getGameTurn().endTurn();
        }
    }

    public void setShopPosition(double xPos, double yPos){
        model.setShopPosition(new Translate(xPos, yPos));
        setShopItemPositions();
    }

    public void setShopItemPositions(){

        for(int i = 0; i < 6; i++){
            Translate pos = new Translate(model.getPosition().getX(), model.getPosition().getY() + i*100);
            model.addItemPosition(pos);

        }
    }

    public void registerObserver(ShopObserver obs) {
        model.register(obs);
    }

    public void createRandomShopItem(){
        String race = model.getRandomRace();
        String power = model.getRandomPower();
        model.addShopItem(new CombinationController(race, power));

    }

    private void createSpecificShopItems(String race, String power) {
        model.addShopItem(new CombinationController(race, power));
    }

    public List<CombinationController> getShopItems(){
        return model.getShopItems();
    }

    @Override
    public void update(DocumentSnapshot ds) {
        if(ds.getString("id").equals("buy")){

            return;
        }
        if(ds.getString("id").equals("add")){
            String race = ds.getString("race");
            String power = ds.getString("power");
            createSpecificShopItems(race, power);
            return;
        }
    }

    public void createCombinationView(int index, Group group) {
        gameCon.createCombinationView(group, model.getShopItems().get(index));
    }
}
