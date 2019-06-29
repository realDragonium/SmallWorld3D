package Model;

import Enum.TurnFase;
import Observable.TurnObservable;
import Observer.TurnObserver;

import java.util.*;

public class TurnModel implements TurnObservable {


    private List<TurnObserver> observers = new ArrayList<>();
    public int currentTurn = 0;
    private int turnPerRound = 0;
    private List<String> players = new ArrayList<>();
    public String currentPlayerId = "player1";
    private int indexcurrentplayer = 0;
    private TurnFase fase;
    private Queue<TurnFase> fases = new LinkedList<>();


    public TurnModel(int turnPerRound){
        this.turnPerRound = turnPerRound;
        currentTurn = 1;
        makeStack();
        players.add("player1");
        players.add("player2");
        players.add("player3");
        players.add("player4");

    }

    public int getTurnPerRound(){
        return turnPerRound;
    }

    private void makeStack(){
        Collections.addAll(fases, TurnFase.values());
        fase = fases.remove();
    }

    public void nextTurn(){
        indexcurrentplayer++;
        if(indexcurrentplayer > 3)indexcurrentplayer = 0;
        currentPlayerId = players.get(indexcurrentplayer);
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
        return Integer.parseInt(currentPlayerId.split("yer")[1]);
    }

    @Override
    public TurnFase getFase() {
        return fase;
    }

    public void setFase(TurnFase currentPhase) {
        fase = currentPhase;
        notifyObservers();
    }
}
