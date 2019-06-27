package Model;

import Observable.PlayerObservable;
import Observer.PlayerObserver;

public class PlayerModel implements PlayerObservable {
    private PlayerObserver observer;
    private final String NAME;
    private String playerID;
    public int fiches;
    public int points;
    public boolean connected = true;

    public PlayerModel(String playerId, String name) {
        playerID = playerId;
        points = 5;
        NAME = name;
    }

    public PlayerModel(String playerId) {
        playerID = playerId;
        points = 5;
        NAME = playerId;
    }

    public void setFicheAmount(int amount){
        fiches = amount;
    }

    public void removePoints(int amount){
        points -= amount;
        notifyObserver();
    }

    public String getId(){
        return playerID;
    }

    @Override
    public void register(PlayerObserver po) {
        observer = po;
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }

    @Override
    public int getFiches() {
        return fiches;
    }

    @Override
    public int getPoints() {
        return points;
    }

    public void addPunten(int amount) {
        points += amount;
        notifyObserver();
    }
}
