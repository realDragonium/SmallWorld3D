package Model;

import Observable.InLobbyObservable;
import Observer.InLobbyObserver;

import java.util.ArrayList;
import java.util.List;


public class InLobbyModel implements InLobbyObservable {
    private List<InLobbyObserver> observers = new ArrayList<>();
    private boolean start = false;
    private String lobbyNaam;
    private String player1;
    private String player2;
    private String player3;
    private String player4;



    public void setLobbyNaam(String lobbyNaam){
        this.lobbyNaam = lobbyNaam;
    }



    public String getLobbyNaam(){
        return lobbyNaam;
    }

    public void startGame(boolean start){
        this.start = start;
        notifyAllObservers();
    }


    // Notifies all observers in the list of observers
    @Override
    public void notifyAllObservers() {
        for(InLobbyObserver obs : observers) {
            obs.update(this);
        }
    }

    @Override
    public void register(InLobbyObserver ob){
        observers.add(ob);
    }

    @Override
    public void unregister(InLobbyObserver ob){
        observers.remove(ob);
    }

    @Override
    public String getPlayer1() {
        return player1;
    }

    @Override
    public String getPlayer2() {
        return player2;
    }

    @Override
    public String getPlayer3() {
        return player3;
    }

    @Override
    public String getPlayer4() {
        return player4;
    }

    @Override
    public boolean getStart() {
        return start;
    }

    public void setPlayer(int i, String player) {
        switch (i) {
            case 1:
                player1 = player;
                break;
            case 2:
                player2 = player;
                break;
            case 3:
                player3 = player;
                break;
            case 4:
                player4 = player;
                break;
        }
        notifyAllObservers();

        }
    }

