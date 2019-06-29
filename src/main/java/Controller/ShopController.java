package Controller;

import Firebase.FirebaseGameObserver;
import Model.ShopModel;

import Objects.ShopCombination;
import Observer.ShopObserver;
import Objects.PowerOld;
import Power.Power;
import Race.Race;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;

import Enum.RaceEnum;
import Enum.PowerEnum;

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
        gameCon.getFireBase().register("shop", this);
    }

    public void makeItems(){
        createShopItems();
        makeNewCombination();


//        gameCon.getFireBase().shopUpdate(this);
//        gameCon.getFireBase().getShopItems();
    }

    private void removeItem(double item){
        model.removeItem((int)item);
    }

    public void buyingItem(int item) {
        if (model.getShopItems().size() > item) {
            gameCon.getCurrentPlayer().buyFromShop(model.getShopItems().get(item), item);
//            gameCon.getGameTurn().endTurn();
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
            ShopCombination combi = new ShopCombination(race, power);
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

    public List<ShopCombination> getShopItems(){
        return model.getShopItems();
    }

    @Override
    public void update(DocumentSnapshot ds) {
        if(ds.getString("id").equals("buy")){

        }
        else if(ds.getString("id").equals("add")){

        }
    }
}
