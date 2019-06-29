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
import Enum.RaceEnum;
import Enum.PowerEnum;
import Power.Power;
import Race.Race;

public class CombinationModel implements CombinationObservable {

    private List<CombinationObserver> observers = new ArrayList<>();
    private Race race;
    private Power power;
    private boolean active = true;
    private Stack<FicheController> availableFiches = new Stack<>();
    private List<AreaController> areas = new ArrayList<>();

    private AttackType attack;
    private Object pointsCounter;
    private Object defends;
    private Object specialAction;

    public CombinationModel(String raceId, String powerId){
        this.race = RaceEnum.valueOf(raceId).getRace();
        this.power = PowerEnum.valueOf(powerId).getPower();
        attack = new FirstAttack();
    }

    public void addArea(AreaController area) {
        areas.add(area);
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
        return race.getName();
    }

    @Override
    public String getPowerId() {
        return power.getName();
    }

    public void setToNonActive() {
        active = false;
    }

    public AttackType getAttack(){
        return attack;
    }
}
