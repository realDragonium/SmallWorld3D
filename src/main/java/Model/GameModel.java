package Model;

import Controller.PlayerController;
import Observable.GameObservable;
import Observer.GameObserver;
import Enum.GameViewEnum;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameObservable {
    private GameObserver observer;
    private final int numberOfRounds;
    private final int numberOfPlayers;
    public boolean gameEnded = false;
    private List<GameViewEnum> activeViews = new ArrayList<>();
    private List<PlayerController> players = new ArrayList<>();

    public GameModel(int rounds, int players){
        numberOfRounds = rounds;
        numberOfPlayers = players;
    }


    public int getNumberOfRounds(){
        return numberOfRounds;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void addActiveView(GameViewEnum view){
        activeViews.add(view);
        notifyObserver();
    }

    public void removeActiveView(GameViewEnum view){
        activeViews.remove(view);
        notifyObserver();
    }

    public void changeGameView(List<GameViewEnum> views){
        activeViews = views;
        notifyObserver();
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
