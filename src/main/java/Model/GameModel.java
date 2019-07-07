package Model;

import Controller.PlayerController;
import Observable.GameObservable;
import Observer.GameObserver;
import Enums.GameViewEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameModel implements GameObservable {
    private GameObserver observer;
    private final int numberOfRounds;
    public int myPlayerId;
    public boolean gameEnded = false;
    private List<GameViewEnum> activeViews = new ArrayList<>();
    private List<PlayerController> players;
    private String gameSpeed;
    public HashMap<String, Object> lobbyInfo = new HashMap<>();

    public GameModel(int rounds){
        numberOfRounds = rounds;
    }

    public void setPlayers(List<PlayerController> players){
        this.players = players;

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

    public void setGameSpeed(String gameSpeed) {
        this.gameSpeed = gameSpeed;
    }
}
