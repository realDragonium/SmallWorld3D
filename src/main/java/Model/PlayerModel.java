package Model;

import Controller.CombinationController;
import Controller.FicheController;
import Observable.PlayerObservable;
import Observer.PlayerObserver;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlayerModel implements PlayerObservable {

    private Translate player3dPos;
    private Translate player2dPos;
    private List<PlayerObserver> observers = new ArrayList<>();
    private String name = "";
    private int playerID;
    public List<CombinationController> combinations = new ArrayList<>();
    private List<CombinationController> declineCombies = new ArrayList<>();
    private CombinationController currentCombi;
    public int points;

    public PlayerModel(int playerId) {
        playerID = playerId;
        points = 5;
    }

    public PlayerModel(String name) {
        this.name = name;
    }

    public List<CombinationController> getCombies(){
        return combinations;
    }

    public void addCombi(CombinationController combi){
        currentCombi = combi;
        combinations.add(combi);
        notifyObserver();
    }

    public void declineCombi(CombinationController combi){
        currentCombi = null;
        declineCombies.add(combi);
        notifyObserver();
    }

    public void removeCombi(CombinationController combi){
        declineCombies.remove(combi);
        combinations.remove(combi);
    }

    public void setPlayer3dPos(Translate position){
        player3dPos = position;
    }

    public void setPlayer2dPos(Translate position) {player2dPos = position;}

    public void removePoints(int amount){
        points -= amount;
        notifyObserver();
    }

    public int getId(){
        return playerID;
    }

    @Override
    public void register(PlayerObserver po) {
        observers.add(po);
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        for(PlayerObserver po: observers){
            po.update(this);
        }
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public boolean hasActiveCombination() {
        return currentCombi != null;
    }

    @Override
    public CombinationController getCurrentCombi() {
        return currentCombi;
    }

    @Override
    public List<CombinationController> getDeclineCombies(){
        return declineCombies;
    }

    public void addPunten(int amount) {
        points += amount;
        notifyObserver();
    }

    public Translate get3dPos() {
        return player3dPos;
    }

    public Translate get2dPos() {
        return player2dPos;
    }



    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObserver();
    }
}
