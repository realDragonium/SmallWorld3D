package Model;

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
    private LinkedList<Turn> turns = new LinkedList<>();
    private int myPlayerId;

    public TurnModel(List<PlayerController> players, int myPlayerId){
        this.myPlayerId = myPlayerId;
        this.players = players;
    }

    public LinkedList<Turn> getTurns(){
        return turns;
    }

    public void newRound(){
        turns.add(new NotMyTurn());
        turns.add(new NotMyTurn());
        turns.add(new NotMyTurn());
        turns.add(myPlayerId, new MyTurn());
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
}
