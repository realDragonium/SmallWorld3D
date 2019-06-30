package Controller;

import Firebase.FirebaseGameObserver;
import Model.ShopModel;

import Objects.ShopCombination;
import Observer.ShopObserver;
import Objects.PowerOld;
import Power.Power;
import Race.Race;
import View.CombinationView;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;

import Enum.RaceEnum;
import Enum.PowerEnum;
import javafx.scene.Group;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopController implements FirebaseGameObserver {

    GameController gameCon;
    ShopModel model = new ShopModel();
    private List<String> races = new ArrayList<>();
    private List<String> powers = new ArrayList<>();

    ShopController(GameController gameCon) {
        this.gameCon = gameCon;
    }

    public void makeItems(){
        createShopItems();
        makeNewCombination();
        makeNewCombination();
        makeNewCombination();
        makeNewCombination();
        makeNewCombination();
        makeNewCombination();


//        gameCon.getFireBase().shopUpdate(this);
//        gameCon.getFireBase().getShopItems();
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

    private void createShopItems() {
        RaceEnum[] tempRaces = RaceEnum.values();
        PowerEnum[] tempPowers = PowerEnum.values();

        for(int i = 0; i< 6; i++) {
            races.add(tempRaces[(int) (Math.random() * tempRaces.length)].getRace().getName());
            powers.add(tempPowers[(int) (Math.random() * tempPowers.length)].getPower().getName());
        }

    }

    private void makeNewCombination() {
        String race = getRandomRace();
        String power = getRandomPower();
        if (race != null && power != null) {

            CombinationController combi = new CombinationController(race, power);
            model.addShopItem(combi);

        }
    }
    private String getRandomRace() {
        if (races.size() != 0) {
            return races.remove(0);
        }
        return null;
    }

    private String getRandomPower() {
        if (powers.size() != 0) {
            return powers.remove(0);
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

    public void createCombinationView(int index, Group group) {
        gameCon.createCombinationView(group, model.getShopItems().get(index));
    }
}
