package Controller;

import Enums.AreaInfoEnum;
import Enums.AreaProperty;
import Enums.AreaType;
import Model.AreaModel;
import Objects.AreaInfo;
import Observer.AreaObserver;
import View.Crystal;
import View.NumberView;
import View.SpecialProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class AreaController{
    private MapController mapCon;
    private AreaModel model;
    private GameController gameCon;
    private NumberController numberCon;

    public AreaController(AreaInfo info, MapController map, GameController gameCon){
        mapCon = map;
        model = new AreaModel(info);

        this.gameCon = gameCon;
    }

    public void fixNeighboursControllers(){
        List<AreaController> areas = new ArrayList<>();
        for(String areaId: model.getNeigbours())
            areas.add(mapCon.getAreaCon(areaId));
        model.neighbourCons = areas;
    }

    public List<AreaController> getNeighbourCons(){
        return model.neighbourCons;
    }

    public void addFiche(FicheController fiche){
        model.addFiche(fiche);
    }

    public void addFiche(){
        model.addFiche(model.getOwningCombi().getFiche());
    }

    public FicheController removeFiche(){
       return model.removeFiche();
    }

    public void returnFiche(){
        model.getOwningCombi().addRaceFiche(model.removeFiche());
    }

    public void attackArea(Stack<FicheController> fiches) {
        if(model.getNumberOfFiches() > 0) {
            returnAllFiches();
            model.getOwningCombi().retreat(this);
        }
        fiches.forEach(this::addFiche);
    }

    public void leaveArea(){
        returnAllFiches();
        model.setOwningCombi(mapCon.getLostTribeCombi());
        setAreaInfoButton(AreaInfoEnum.NONE);
    }

    private void returnAllFiches(){
        int number = model.getNumberOfFiches();
        for(int i = 0; i < number; i++)
            model.getOwningCombi().addRaceFiche(removeFiche());
        model.getOwningCombi().removeArea(this);
    }

    public void putFiche(FicheController fiche){
        model.addFiche(fiche);
    }

    public Translate getAreaPoint(){
        return model.getAreaPoint();
    }

    public String getId() {
        return model.getId();
    }

    void changeCombiOwner(CombinationController combi){
        removeOwner();
        model.setOwningCombi(combi);
    }

    void removeOwner(){
        if(model.getOwningCombi()!= null) model.getOwningCombi().removeArea(this);
    }

    public PlayerController getOwnerPlayer() {
        return getOwnerCombi().getPlayer();
    }

    CombinationController getOwnerCombi(){
        return model.getOwningCombi();
    }

    void changeActive() {
        model.changeActive();
    }

    public void register(AreaObserver ao) {
        model.register(ao);
    }

    public void selectActive() {
        mapCon.selectSingleArea(this);
    }

    public void makeActive(){ model.changeActive();}

    public AreaProperty getSpecialProp() {
        return model.getSpecialProp();
    }

    public boolean isNextToWater() {
        return model.isNextToWater();
    }

    int getFichesAmount() {
        return model.getNumberOfFiches();
    }

    public AreaType getAreaType() {
        return model.getAreaType();
    }

    public boolean isBorderArea(){return model.isBorderArea();}

    public boolean isAttackAble() {
        return model.isAttackAble();
    }

    public List<String> getNeighbours() {
        return model.getNeigbours();
    }

    public void hoverEntered() {
        model.changeHoverState();
    }

    public void hoverExited() {
        model.changeHoverState();
    }

    public int getDefenceValue() {
        int number = 2 + model.getNumberOfFiches();
        if(model.getAreaType() == AreaType.mountain) number++;
        return number;
    }

    public void createNumber(Group group){
        numberCon = new NumberController();
        new NumberView(numberCon, group);
    }

    public boolean isLostTribe(){
        return model.lostTribe;
    }



    public void setNumber(int number){
        numberCon.setNumber(number);
    }

    public void createSpecialPropFiche(){
        if(model.getSpecialProp() != AreaProperty.None) {

            if(model.getSpecialProp() == AreaProperty.Magical){
                new Crystal().setPosition(model.getSpecialPropPoint());
            }
            else {
                Node mesh = new SpecialProperty(model.getSpecialProp()).create();
                int scale = 5;

                mesh.setRotationAxis(Rotate.Y_AXIS);
                mesh.setRotate(ThreadLocalRandom.current().nextInt(0, 360 + 1));

                mesh.setTranslateX(model.getSpecialPropPoint().getX());
                mesh.setTranslateY(model.getSpecialPropPoint().getY());
                mesh.setTranslateZ(model.getSpecialPropPoint().getZ());
                mesh.setScaleX(scale);
                mesh.setScaleY(scale);
                mesh.setScaleZ(scale);
            }
        }
    }

    public void showInfo() {
        gameCon.getAreaInfoCon().putAreaInformationScreen(this);
    }

    public void setAreaPoint(Translate translate) {
        model.areaPoint = translate;
    }

    public void setPropPoint(Translate translate) {
        model.specialPropPoint = translate;
        createSpecialPropFiche();
    }

    public List<AreaInfoEnum> getAreaInfoButtons(){
        return model.getAreaInfoButtons();
    }

    public void setAreaInfoButton(AreaInfoEnum areaInfoButton){
        model.setAreaInfoButton(areaInfoButton);
    }
    public void resetAreaInfo(){
        model.resetAreaInfoButton();
    }

    public void placeLostTribe() {
        if(model.lostTribe)
            addFiche(mapCon.getLostTribeCombi().getFiche());
    }
}
