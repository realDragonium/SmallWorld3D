package Model;

import Attacks.AttackType;
import Attacks.FirstAttack;
import Controller.AreaController;
import Controller.CombinationController;
import Controller.FicheController;
import Controller.RaceController;
import Fiches.RaceFiche;
import Observable.CombinationObservable;
import Observer.CombinationObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class CombinationModel implements CombinationObservable {

    private List<CombinationObserver> observers = new ArrayList<>();
    private String raceId;
    private String powerId;
    private boolean active = true;
    private Stack<FicheController> availableFiches = new Stack<>();
    private AttackType attack;

    public CombinationModel(String raceId, String powerId){
        this.raceId = raceId;
        this.powerId = powerId;
        attack = new FirstAttack();
    }

    public boolean isActive(){
        return active;
    }

    public Stack<FicheController> removeFiches(int count) {
        Stack<FicheController> tempFiches = new Stack<>();
        for(int i = 0; i < count; i++){
            tempFiches.add(availableFiches.pop());
        }
        return tempFiches;
    }

    @Override
    public void register(CombinationObserver mvo) {
        observers.add(mvo);
        notifyAllObservers();
    }

    @Override
    public void unregister(CombinationObserver mvo) {
        observers.remove(mvo);
    }

    @Override
    public void notifyAllObservers() {
        for(CombinationObserver obs : observers){
            obs.update(this);
        }
    }

    @Override
    public String getRaceId() {
        return raceId;
    }

    @Override
    public String getPowerId() {
        return powerId;
    }

    public void setToNonActive() {
        active = false;
    }

    public AttackType getAttack(){
        return attack;
    }
}
