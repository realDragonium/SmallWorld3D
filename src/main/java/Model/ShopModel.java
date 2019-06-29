package Model;

import Objects.ShopCombination;
import Observable.ShopObservable;
import Observer.ShopObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShopModel implements ShopObservable {

    private List<ShopCombination> shopItems = new LinkedList<>();
    private List<ShopObserver> observers = new ArrayList<>();

    public List<ShopCombination> getShopItems(){
        return shopItems;
    }

    public void addShopItem(ShopCombination item){
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
            return shopItems.get(item).getRace();
        }
        else{
            return "none";
        }
    }

    @Override
    public String getPower(int item) {
        if(shopItems.size() > item) {
            return shopItems.get(item).getPower();
        }
        else{
            return "none";
        }
    }
}
