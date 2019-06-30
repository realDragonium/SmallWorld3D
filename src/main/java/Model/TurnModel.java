package Model;

import Controller.PlayerController;
import Observable.TurnObservable;
import Observer.TurnObserver;
import Turn.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TurnModel implements TurnObservable {
    private List<TurnObserver> observers = new ArrayList<>();
    public int currentTurn = 0;
    public PlayerController currentPlayer;
    public List<PlayerController> players;
    private Stack<Turn> turns = new Stack<>();
    private int myPlayerId;

    public TurnModel(List<PlayerController> players, int myPlayerId){
        this.myPlayerId = myPlayerId;
        this.players = players;
        currentTurn = 0;
    }

    public Stack<Turn> getTurns(){
        return turns;
    }

    public void newRound(){
        this.turns.add(new NotMyTurn());
        this.turns.add(new NotMyTurn());
        this.turns.add(new NotMyTurn());
        this.turns.add(myPlayerId, new MyTurn());
    }

    @Override
    public void register(TurnObserver to) {
        observers.add(to);
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        observers.forEach(ob -> ob.update(this));
    }

    @Override
    public String getPlayerName() {
        return currentPlayer.getId();
    }
}
