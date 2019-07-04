package Model;

import Controller.AreaController;
import Controller.CombinationController;
import Controller.FicheController;
import Controller.PlayerController;
import Enums.AreaProperty;
import Enums.AreaType;
import Enums.AreaInfoEnum;
import Objects.AreaInfo;
import Observable.AreaObservable;
import Observer.AreaObserver;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AreaModel implements AreaObservable {

    private boolean borderArea;
    private AreaProperty specialProperty;
    private List<String> neighbours;
    public List<AreaController> neighbourCons;
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
    private List<AreaInfoEnum> areaInfoButtons;

    public AreaModel(AreaInfo info) {
        id = info.id;
        neighbours = info.neighbours;
        borderArea = info.borderArea;
        attackAble = info.attackable;
        lostTribe = info.lostTribe;
        specialProperty = AreaProperty.valueOf(info.property);
        nextToWater = info.nextToWater;
        type = AreaType.valueOf(id.split("_")[0]);
        areaInfoButtons = new ArrayList<>();
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

    public FicheController removeFiche() {
        FicheController fiche = fiches.pop();
        notifyObserver();
        return fiche;
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

    public AreaProperty getSpecialProp(){
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

    public List<AreaInfoEnum> getAreaInfoButtons(){
        return areaInfoButtons;
    }

    public void setAreaInfoButton(AreaInfoEnum areaInfoButton){
        this.areaInfoButtons.add(areaInfoButton);
    }

    public void resetAreaInfoButton(){
        this.areaInfoButtons = new ArrayList<>();
    }

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

    @Override
    public Translate getAreaPoint(){
        return areaPoint;
    }

}
