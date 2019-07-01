package Model;

import Controller.CombinationController;
import Controller.FicheController;
import Controller.RaceController;
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
    private String playerID;
    public Stack<FicheController> raceFiches = new Stack<>();
    private List<CombinationController> combinations = new ArrayList<>();
    private Stack<CombinationController> useableCombies = new Stack<>();
    private CombinationController currentCombi;
    public int points;

    public boolean connected = true;

    public List<CombinationController> getCombies(){
        return combinations;
    }

    public void addCombi(CombinationController combi){
        combinations.add(combi);
    }

    public void nextCombi(){
        currentCombi = useableCombies.pop();
    }

    public PlayerModel(String playerId) {
        playerID = playerId;
        points = 5;
        NAME = playerId;
    }

    public void setPlayer3dPos(Translate position){
        player3dPos = position;
    }

    public void setPlayer2dPos(Translate position) { player2dPos = position;}

    public void addRaceFiche(FicheController fiche){

    }

    public FicheController removeRaceFiche(){
        return raceFiches.pop();
    }

    public void removePoints(int amount){
        points -= amount;
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
    public int getRaceFichesAmount() {
        return raceFiches.size();
    }

    @Override
    public int getPoints() {
        return points;
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
}
