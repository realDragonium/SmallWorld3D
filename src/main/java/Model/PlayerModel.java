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
    private PlayerObserver observer;
    private final String NAME;
    private int playerID;
    private List<CombinationController> combinations = new ArrayList<>();
    private List<CombinationController> activeCombies = new ArrayList<>();
    private List<CombinationController> declineCombies = new ArrayList<>();
    private CombinationController currentCombi;
    public int points;

    public PlayerModel(int playerId, String Name) {
        playerID = playerId;
        points = 5;
        NAME = Name;
    }

    public PlayerModel(String name) {
        NAME = name;
    }

    public void setCurrentCombi(CombinationController combi){
        System.out.println("PlayerModel: "+combi);
        currentCombi = combi;
    }

    public List<CombinationController> getCombies(){
        return combinations;
    }
    public List<CombinationController> getActiveCombies(){
        return activeCombies;
    }
    public List<CombinationController> getDeclineCombies(){
        return declineCombies;
    }

    public void addCombi(CombinationController combi){
        currentCombi = combi;
        combinations.add(combi);
        activeCombies.add(combi);
        notifyObserver();
    }

    public void declineCombi(CombinationController combi){
        activeCombies.remove(combi);
        declineCombies.add(combi);
    }

    public void removeCombi(CombinationController combi){
        activeCombies.remove(combi);
        declineCombies.remove(combi);
        combinations.remove(combi);
    }

    public void setPlayer3dPos(Translate position){
        player3dPos = position;
    }

    public void setPlayer2dPos(Translate position) { player2dPos = position;}

    public void removePoints(int amount){
        points -= amount;
        notifyObserver();
    }

    public int getId(){
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
    public int getPoints() {
        return points;
    }

    @Override
    public boolean hasActiveCombination() {
        return combinations.size() > 0;
    }

    @Override
    public String getActiveCombi() {
        return currentCombi.getRace();
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

    public CombinationController getCurrenCombi() {
        return currentCombi;
    }

    public String getName() {
        return NAME;
    }
}
