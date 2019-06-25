package Model;

import Observable.CombinationObservable;
import Observer.CombinationObserver;

import java.util.ArrayList;
import java.util.List;

public class CombinationModel implements CombinationObservable {

    private List<CombinationObserver> observers = new ArrayList<>();
    private String raceId;
    private String powerId;
    private boolean active = true;

    public CombinationModel(String raceId, String powerId){
        this.raceId = raceId;
        this.powerId = powerId;
    }


    public boolean isActive(){
        return active;
    }

    @Override
    public void register(CombinationObserver mvo) {
        observers.add(mvo);
        notifyAllObservers();
    }

    @Override
    public void unregister(CombinationObserver mvo) {
        observers.remove(mvo);
    }

    @Override
    public void notifyAllObservers() {
        for(CombinationObserver obs : observers){
            obs.update(this);
        }
    }

    @Override
    public String getRaceId() {
        return raceId;
    }

    @Override
    public String getPowerId() {
        return powerId;
    }

    public void setToNonActive() {
        active = false;
    }
}
