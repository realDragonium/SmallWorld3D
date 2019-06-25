package Model;

import Controller.PlayerController;
import Enum.AreaProperty;
import Enum.AreaType;
import Objects.RaceFiche;
import Observable.AreaObservable;
import Observer.AreaObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class AreaModel implements AreaObservable {

    private Stack<RaceFiche> raceFiches = new Stack<>();
    private boolean active = false;
    private AreaObserver observer;
    private String id;
    public PlayerController player;
    private AreaType type;
    private boolean nextToWater = false;
    private boolean borderArea = false;
    private AreaProperty specialProperty = AreaProperty.none;
    private List<String> neighbours = new ArrayList<>();
    private boolean attackAble = true;

    public AreaModel(String id) {
        this.id = id;
        type = AreaType.valueOf(id.split("_")[0]);
    }

    public void setFiches(int fiches){
        raceFiches = new Stack<>();
        IntStream.range(0, fiches).forEach(o -> raceFiches.push(new RaceFiche()));
        notifyObserver();
    }

    public void setNeighbours(List<String> neighbour){
        neighbours = neighbour;
    }

    public PlayerController getPlayer(){
        return player;
    }

    public void setBorderArea(boolean bArea){
        borderArea = bArea;
    }

    public boolean firstAttackArea(){
        return borderArea && attackAble;
    }

    public void setAreaType(String type){
        specialProperty = AreaProperty.valueOf(type);
    }

    public String getId() {
        return id;
    }

    public void changeActive() {
        active = !active;
        notifyObserver();
    }

    //+2 vanwege 1 extra per fiche en het land is 2 verdedingspunt waard
    public int numbersNeeded() {
        return raceFiches.size() + 2;
    }

    //Ze worden overschreven omdat getAllFiches de hele lijst al mee geeft
    public void setFiches(Stack<RaceFiche> fiches) {
        raceFiches = fiches;
        notifyObserver();
    }

    public Stack<RaceFiche> getAllFiches() {
        Stack<RaceFiche> tempFiches = raceFiches;
        raceFiches = new Stack<>();
        return tempFiches;
    }

    public AreaProperty getSpecialProp(){
        return specialProperty;
    }

    public boolean isNextToWater(){
        for(String area : neighbours){
            if(area.split("_")[0].equals("water")){
                return true;
            }
        }
        return false;
    }

    public Stack<RaceFiche> getAllButOne(){
        Stack<RaceFiche> temp = new Stack<>();
        if(raceFiches.size() > 0) {
            RaceFiche tempFiche = raceFiches.pop();
            temp  = raceFiches;
            raceFiches = new Stack<>();
            raceFiches.add(tempFiche);
            notifyObserver();
        }
        return temp;
    }

    public RaceFiche getOneFiche() {
        RaceFiche tempFiche = raceFiches.pop();
        notifyObserver();
        return tempFiche;
    }

    public void addFiche(RaceFiche fiche) {
        raceFiches.add(fiche);
        notifyObserver();
    }

    public AreaType getAreaType() {
        return type;
    }

    public void setAttackAble(boolean attackAble) {
        this.attackAble = attackAble;
    }

    public boolean isAttackAble(){return attackAble; }

    public List<String> getNeigbours() {return neighbours;}

    @Override
    public void register(AreaObserver ao) {
        observer = ao;
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public int getNumberOfFiches() {
        return raceFiches.size();
    }
}
