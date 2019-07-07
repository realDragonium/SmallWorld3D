package Model;

import Observable.RoundObservable;
import Observer.RoundObserver;

import java.util.ArrayList;
import java.util.List;

public class RoundModel implements RoundObservable {

    private int maxRounds;
    public int currentRound = 0;
    private List<RoundObserver> observers = new ArrayList<>();

    public RoundModel(int maxRounds){
        this.maxRounds = maxRounds;
    }

    public int getMaxRounds(){
        return maxRounds;
    }

    public void nextRound(){
        currentRound++;
        notifyObservers();
    }

    @Override
    public void register(RoundObserver ro) {
        observers.add(ro);
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(RoundObserver ro : observers){
            ro.update(this);
        }
    }

    @Override
    public int getRound() {
        return currentRound;
    }
}
