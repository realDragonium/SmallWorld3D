package Model;

import Observable.TurnObservable;
import Observer.TurnObserver;

import java.util.ArrayList;
import java.util.List;

public class TurnModel implements TurnObservable {

    private List<TurnObserver> observers = new ArrayList<>();
    public int currentTurn = 0;
    private int turnPerRound = 0;
    private List<String> players = new ArrayList<>();
    public String currentPlayerId = "player1";
    private int indexcurrentplayer = 0;


    public TurnModel(int turnPerRound){
        this.turnPerRound = turnPerRound;
        currentTurn = 1;
        players.add("player1");
        players.add("player2");
        players.add("player3");
        players.add("player4");

    }

    public void nextTurn(){
        currentTurn++;
        notifyObservers();
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
    public int getTurn() {
        return currentTurn;
    }
}
