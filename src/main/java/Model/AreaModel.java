package Model;

import Controller.CombinationController;
import Controller.FicheController;
import Controller.PlayerController;
import Enum.AreaType;
import Objects.AreaInfo;
import Observable.AreaObservable;
import Observer.AreaObserver;
import javafx.scene.transform.Translate;

import java.util.List;
import java.util.Stack;

public class AreaModel implements AreaObservable {

    private boolean borderArea;
    private String specialProperty;
    private List<String> neighbours;
    private String id;
    private AreaType type;
    private boolean lostTribe;
    private boolean nextToWater;
    private boolean attackAble;

    private boolean active = false;
    private AreaObserver observer;
    private CombinationController owningCombination;
    public PlayerController player;
    private boolean hovering = false;
    private Stack<FicheController> fiches = new Stack<>();
    public Translate areaPoint;
    public Translate specialPropPoint;


    public AreaModel(String id, Translate areaPoint) {
        this.id = id;
        this.areaPoint = areaPoint;
        type = AreaType.valueOf(id.split("_")[0]);
    }

    public AreaModel(AreaInfo info) {
        id = info.id;
        neighbours = info.neighbours;
        borderArea = info.borderArea;
        attackAble = info.attackable;
        lostTribe = info.lostTribe;
        specialProperty = info.property;
        nextToWater = info.nextToWater;

        type = AreaType.valueOf(id.split("_")[0]);
    }

    public AreaModel(String id) {
        this.id = id;
    }

    @Override
    public Translate getAreaPoint(){
        return areaPoint;
    }

    public Translate getSpecialPropPoint(){
        return specialPropPoint;
    }

    public void addFiche(FicheController fiche){
        fiches.add(fiche);
        double height = areaPoint.getY();
        height = height - (fiches.size() - 1) * 10;
        Translate fichePoint = new Translate(areaPoint.getX(), height, areaPoint.getZ());
        fiche.moveToPosition(fichePoint);
        notifyObserver();
    }

    public PlayerController getPlayer(){
        return player;
    }


    public boolean firstAttackArea(){
        return borderArea && attackAble;
    }

    public String getId() {
        return id;
    }

    public void changeHoverState(){
        hovering = !hovering;
        notifyObserver();
    }

    public void changeActive() {
        active = !active;
        notifyObserver();
    }

    public void setFiches(Stack<FicheController> fiches) {
        this.fiches = fiches;
        notifyObserver();
    }



    public Stack<FicheController> getFiches() {
        return fiches;
    }

    public String getSpecialProp(){
        return specialProperty;
    }

    public boolean isNextToWater(){ return nextToWater; }

    public AreaType getAreaType() {
        return type;
    }

    public void setOwningCombi(CombinationController combi){
        owningCombination = combi;
    }

    public CombinationController getOwningCombi(){
        return owningCombination;
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
        return fiches.size();
    }

    @Override
    public boolean isHovering() {
        return hovering;
    }

    @Override
    public boolean isShowing() {
        return false;
    }


    public FicheController removeFiche() {
        return fiches.pop();
    }
}
