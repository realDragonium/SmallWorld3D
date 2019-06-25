package Model;

import Observable.PlayerObservable;
import Observer.PlayerObserver;

public class PlayerModel implements PlayerObservable {
    private PlayerObserver observer;
    private String playerID;
    public int fiches;
    public int punten;

    public PlayerModel(String playerId) {
        playerID = playerId;
        punten = 5;
    }

    public void setFicheAmount(int amount){
        fiches = amount;
    }

    public void removePoints(int amount){
        punten -= amount;
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
    public int getPunten() {
        return punten;
    }

    public void addPunten(int amount) {
        punten += amount;
        notifyObserver();
    }
}
