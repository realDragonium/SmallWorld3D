package Model;

import Controller.CombinationController;
import Objects.ShopCombination;
import Observable.ShopObservable;
import Observer.ShopObserver;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShopModel implements ShopObservable {

    private List<Translate> itemPositions = new ArrayList<>();
    private List<CombinationController> shopItems = new ArrayList<>();
    private List<ShopObserver> observers = new ArrayList<>();
    private Translate position;


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
