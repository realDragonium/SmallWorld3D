package Model;

import Controller.CombinationController;
import Observable.ShopObservable;
import Observer.ShopObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShopModel implements ShopObservable {

    private List<CombinationController> shopItems = new LinkedList<>();
    private List<ShopObserver> observers = new ArrayList<>();

    public List<CombinationController> getShopItems(){
        return shopItems;
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

    @Override
    public String getRace(int item) {
        if(shopItems.size() > item) {
            return shopItems.get(item).getRace().getId();
        }
        else{
            return "none";
        }
    }

    @Override
    public String getPower(int item) {
        if(shopItems.size() > item) {
            return shopItems.get(item).getPowerOld().getId();
        }
        else{
            return "none";
        }
    }
}
