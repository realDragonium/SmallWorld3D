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
import javafx.scene.transform.Translate;

public class CombinationModel implements CombinationObservable {

    private List<CombinationObserver> observers = new ArrayList<>();
    private Race race;
    private Power power;
    private int newFiches = 0;
    private boolean active = true;
    private Stack<FicheController> raceFiches = new Stack<>();
    private List<AreaController> areas = new ArrayList<>();
    private Translate position;


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

    public void removeArea(AreaController area) {areas.remove(area);}

    public boolean isActive(){
        return active;
    }

    public Stack<FicheController> removeFiches(int count) {
        Stack<FicheController> tempFiches = new Stack<>();
        for (int i = 0; i < count; i++) {
            tempFiches.add(raceFiches.pop());
        }
        System.out.println("Available: "+raceFiches.size());
        return tempFiches;
    }

    public void goIntoDecline() {
        active = false;
    }

    public AttackType getAttack(){
        return attack;
    }

    public void setPosition(Translate pos) {
        position = pos;
        notifyAllObservers();
    }

    public void addFiche(FicheController fiche) {
        raceFiches.add(fiche);
    }

    public FicheController removeFiche() {
        return raceFiches.pop();
    }

    public int getFichesAmount() {
        return raceFiches.size();
    }

    public Race getRace() {
        return race;
    }

    public Power getPower(){
        return power;
    }

    public void setAttackType(AttackType attack) {
        this.attack = attack;
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

    @Override
    public Translate getPosition() {
        return position;
    }

}
