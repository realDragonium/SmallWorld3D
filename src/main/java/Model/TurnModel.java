package Model;

import Controller.CombinationController;
import Controller.PlayerController;
import Observable.TurnObservable;
import Observer.TurnObserver;
import Turn.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TurnModel implements TurnObservable {
    private List<TurnObserver> observers = new ArrayList<>();
    public PlayerController currentPlayer;
    public List<PlayerController> players;
    public LinkedList<Turn> turns = new LinkedList<>();
    public final int myPlayerId;
    public Turn currentTurn;
    public CombinationController currentCombi;

    public TurnModel(List<PlayerController> players, int myPlayerId){
        this.myPlayerId = myPlayerId;
        this.players = players;
    }

    @Override
    public void register(TurnObserver to) {
        observers.add(to);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(ob -> ob.update(this));
    }

    @Override
    public String getPlayerName() {
        return currentPlayer.getName();
    }

    @Override
    public String getRaceName() {
        if(currentCombi == null) return "";
        return currentCombi.getRaceName();
    }

}
