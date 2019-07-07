package Model;

import Observable.InLobbyObservable;
import Observer.InLobbyObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InLobbyModel implements InLobbyObservable {
    private List<InLobbyObserver> observers = new ArrayList<>();
    private boolean start = false;
    private String lobbyNaam = "";
    private String myName;
    private String myPlayer;
    private int lobbyId;
    private String password = "";
    private String gameSpeed = "normal";
    private boolean host = false;
    private HashMap<String, Boolean> playerStates = new HashMap<>();
    private HashMap<String, String> playerNames = new HashMap<>();



    public void setLobbyNaam(String lobbyNaam){
        this.lobbyNaam = lobbyNaam;
    }

    public String getLobbyNaam(){
        return lobbyNaam;
    }

    public void startGame(boolean start){
        this.start = start;
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
    public String getPlayer(int i) {
        return playerNames.get("player" + i);
    }

    @Override
    public boolean getPlayerState(int i) {
        return playerStates.get("player" + i);
    }

    @Override
    public boolean getStart() {
        return start;
    }

    @Override
    public boolean isHost() {
        return host;
    }

    @Override
    public int getPlayersInLobby() {
        int playerAmount = playerNames.size();
        if(playerNames.size() > playerStates.size()) playerAmount = playerStates.size();
        return playerAmount;
    }

    public void setPlayer(int i, String name) {
        playerNames.put("player" + i, name);
    }

    public void setAsHost() {
        host = true;
        notifyAllObservers();
    }

    public void setPlayerReady(int i, boolean state) {
        playerStates.put("player" + i, state);
    }

    public void setLobbyId(int id) {
        lobbyId = id;
    }

    public HashMap<String, String> getPlayerNames() {
        return playerNames;
    }

    public HashMap<String, Boolean> getPlayerStates() {
        return playerStates;
    }

    public String getPassword() {
        return password;
    }

    public String getGameSpeed() {
        return gameSpeed;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMyName(String name) {
        myName = name;
    }

    public void reset(){
        playerNames = new HashMap<>();
        playerStates = new HashMap<>();
    }

    public void setMyPlayer(String player){
        myPlayer = player;
    }

    public String getMyName(){
        return myName;
    }

    public String getMyPlayer() {
        return myPlayer;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setGameSpeed(String value) {
        gameSpeed = value;
    }
}

