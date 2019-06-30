package Model;

import Controller.CombinationController;
import Enum.PowerEnum;
import Enum.RaceEnum;
import Observable.ShopObservable;
import Observer.ShopObserver;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
    }
    private void createNewPowerList(){
        Arrays.asList(PowerEnum.values()).forEach(power -> powers.add(power.getPower().getName()));
    }

    public String getRandomRace(){
        return races.remove((int) (Math.random() * races.size()));
    }

    public String getRandomPower(){
        return powers.remove((int) (Math.random() * powers.size()));
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

    public CombinationController getShopItem(int item){
        CombinationController combo = shopItems.remove(item);
        notifyObservers();
        return combo;

    }

    public void addShopItem(CombinationController item){
        shopItems.add(item);
        notifyObservers();
    }

    public void removeItem(int item){
        shopItems.remove(item);
        notifyObservers();
    }

    @Override
    public void register(ShopObserver so) {
        observers.add(so);
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
    }

    public Translate getPosition() {
        return position;
    }
}
