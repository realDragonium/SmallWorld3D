package Model;

import Observable.GameObservable;
import Observer.GameObserver;
import Enum.GameViewEnum;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameObservable {
    private GameObserver observer;
    private int numberOfRounds;
    private int turnsARound;
    public boolean gameEnded = false;
    List<GameViewEnum> activeViews = new ArrayList<>();

    public GameModel(int rounds, int turn){
        numberOfRounds = rounds;
        turnsARound = turn;
        activeViews.add(GameViewEnum.BUTTON);
    }

    public int getNumberOfRounds(){
        return numberOfRounds;
    }

    public int getTurnsARound(){
        return turnsARound;
    }

    public void addActiveView(GameViewEnum view){
        activeViews.add(view);
    }

    public void removeActiveView(GameViewEnum view){
        activeViews.remove(view);
    }



    @Override
    public void register(GameObserver go) {
        observer = go;
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }

    @Override
    public List<GameViewEnum> getCurrenViews() {
        return activeViews;
    }
}
