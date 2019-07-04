package Model;

import Attacks.*;
import Controller.AreaController;
import Controller.FicheController;
import Controller.PlayerController;
import Decline.*;
import Enums.PowerEnum;
import Enums.RaceEnum;
import Observable.CombinationObservable;
import Observer.CombinationObserver;
import Phase.Phase;
import Points.*;
import Power.*;
import Race.*;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CombinationModel implements CombinationObservable {

    private List<CombinationObserver> observers = new ArrayList<>();

    public PlayerController player;
    public Stack<FicheController> raceFiches = new Stack<>();
    public List<AreaController> areas = new ArrayList<>();
    public List<AreaController> thisRoundConquered = new ArrayList<>();

    private Translate position;
    public boolean inShop = true;
    public List<AreaController> lastUsedAreas = new ArrayList<>();

    public Race race;
    public Power power;
    public EveryRoundPower everyRoundPower = new None();
    public AttackableAreas attack;
    public AttackableType attackableType;
    public Decline decline;
    public Points points;
    public Object defends;
    public Object specialAction;



    public Points racePoints = new NullPoints();
    public Points powerPoints = new NullPoints();

    public AttackableAreas raceAreas = new NormalAreasAttack();
    public AttackableAreas powerAreas = new NormalAreasAttack();

    public AttackBoost raceAttackBoost = new NoAttackBoost();
    public AttackBoost powerAttackBoost = new NoAttackBoost();

    public CombinationModel(String raceId, String powerId){
        this.race = RaceEnum.valueOf(raceId).getRace();
        this.power = PowerEnum.valueOf(powerId).getPower();
        attack = new NormalAreasAttack();
        decline = new NotInDecline();
        points = new NormalPoints();
        attackableType = new NormalAttackableType();
    }

    public List<AreaController> getAreas(){
        return areas;
    }

    public void addArea(AreaController area) {
        areas.add(area);
        thisRoundConquered.add(area);
    }

    public void removeArea(AreaController area) {
        areas.remove(area);
    }

    public boolean isActive(){
        return decline.isActive();
    }

    public Stack<FicheController> removeFiches(int count) {
        Stack<FicheController> tempFiches = new Stack<>();
        for (int i = 0; i < count; i++)
            tempFiches.add(raceFiches.pop());
        System.out.println("Fiches Over: "+raceFiches.size());
        return tempFiches;
    }

    public void goIntoDecline() {
        decline = new InDecline();
    }

    public AttackableAreas getAttack(){
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

    public Phase getStartingPhase(){
        return decline.startAt();
    }

    public Points getPointCounter(){ return points;}

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
