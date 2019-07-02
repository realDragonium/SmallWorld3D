package Model;

import Controller.PlayerController;
import Observable.GameObservable;
import Observer.GameObserver;
import Enums.GameViewEnum;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameObservable {
    private GameObserver observer;
    private final int numberOfRounds;
    private int imPlayer = 0;
    public boolean gameEnded = false;
    private List<GameViewEnum> activeViews = new ArrayList<>();
    private final List<PlayerController> players;

    public GameModel(int rounds, List<PlayerController> players){
        numberOfRounds = rounds;
        this.players = players;
    }


    public int imPlayer(){
        return imPlayer;
    }

    public int getNumberOfRounds(){
        return numberOfRounds;
    }

    public int getNumberOfPlayers() {
        return players.size();
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

    public PlayerController getPlayer(int id){
        return players.get(id);
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


    public List<PlayerController> getPlayers() {
        return players;
    }
}
