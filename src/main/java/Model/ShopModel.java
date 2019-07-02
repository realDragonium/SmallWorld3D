package Model;

import Controller.CombinationController;
import Enums.PowerEnum;
import Enums.RaceEnum;
import Observable.ShopObservable;
import Observer.ShopObserver;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopModel implements ShopObservable {

    private List<Translate> itemPositions = new ArrayList<>();
    private List<CombinationController> shopItems = new ArrayList<>();
    private List<ShopObserver> observers = new ArrayList<>();
    private Translate position;
    private List<String> races = new ArrayList<>();
    private List<String> powers = new ArrayList<>();

    public ShopModel(){
        creatNewRaceList();
        createNewPowerList();
    }

    private void creatNewRaceList(){
        Arrays.asList(RaceEnum.values()).forEach(race -> races.add(race.getRace().getName()));
        races.remove("losttribes");
    }
    private void createNewPowerList(){
        Arrays.asList(PowerEnum.values()).forEach(power -> powers.add(power.getPower().getName()));
    }

    public String getRandomRace(){
        return races.get((int) (Math.random() * races.size()));
    }

    public String getRandomPower(){
        return powers.get((int) (Math.random() * powers.size()));
    }

    public void removePower(String power){
        powers.remove(power);
    }
    public void removeRace(String race){
        races.remove(race);
    }

    public void addItemPosition(Translate pos ){
        itemPositions.add(pos);
        notifyObservers();
    }

    @Override
    public List<CombinationController> getShopItems(){
        return shopItems;
    }

    @Override
    public Translate getItemPosition(int i) {
        if(itemPositions.size() > i)
        return itemPositions.get(i);
        else return null;
    }

    @Override
    public Translate getShopPosition() {
        return position;
    }

    public CombinationController getShopItem(int item){
        CombinationController combo = shopItems.remove(item);
        notifyObservers();
        return combo;
    }

    public Translate getPosition() {
        return position;
    }

    public void addShopItem(CombinationController item){
        shopItems.add(item);
        notifyObservers();
    }

    public int getShopItemIndex(CombinationController combi){
        return shopItems.indexOf(combi);
    }


    @Override
    public void register(ShopObserver so) {
        observers.add(so);
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(ShopObserver obs : observers){
            obs.update(this);
        }
    }

    @Override
    public int getRound() {
        return 0;
    }

    public void setShopPosition(Translate translate) {
        position = translate;
        notifyObservers();
    }

    public void moveToPosition(Translate translate) {
        position.setX(position.getX() + translate.getX());
        position.setY(position.getY() + translate.getY());
        position.setZ(position.getZ() + translate.getZ());
        notifyObservers();
    }
}
