package Controller;

import Enum.AreaProperty;
import Enum.AreaType;
import Model.AreaModel;
import Fiches.RaceFiche;
import Objects.AreaInfo;
import Observer.AreaObserver;
import View.NumberView;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class AreaController{


    private MapController mapCon;
    private Map3DController map3DCon;
    private AreaModel model;
    private GameController gameCon;
    private NumberController numberCon;
    private FirebaseGameController fb;

    public AreaController(String id, Map3DController mapCon, Translate areaPoint , GameController gameCon){
        model = new AreaModel(id, areaPoint);
        map3DCon = mapCon;
        this.gameCon = gameCon;
        fb = gameCon.getFireBase();

//        loadAreaInFirebase();
//        listenToFirebase();
    }

    public AreaController(Group area, MapController mapController, GameController gameCon) {
        String id = area.getChildren().get(0).getId();
        model = new AreaModel(id);
        mapCon = mapController;
        this.gameCon = gameCon;

        fb = gameCon.getFireBase();
        //createSpecialPropFiche();
//        getAreaInfo(id);
    }

    public AreaController(AreaInfo info, MapController map, GameController gameCon){
        mapCon = map;
        model = new AreaModel(info);
        this.gameCon = gameCon;
    }

//    private void listenToFirebase(){
//        fb.areaListener(this);
//    }
//
//    private void loadAreaInFirebase(){
//        model.setFiches(1);
//        fb.areaUpdate(this);
//    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////ALLEEN DEZE TWEE GEBRUIKEN VOOR AANVALLEN///////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public void addFiche(FicheController fiche){
        model.addFiche(fiche);
    }

    public void removeFiche(){
        FicheController fiche = model.removeFiche();
        fiche.getCombiCon().addRaceFiche(fiche);

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////ALLEEN DEZE TWEE GEBRUIKEN VOOR AANVALLEN///////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void attackArea(Stack<FicheController> fiches) {
        model.getOwningCombi().retreat(this);
        fiches.forEach(fiche -> addFiche(fiche));
    }

    public void leaveArea(){

    }




    public void putFiche(FicheController fiche){
        model.addFiche(fiche);
    }

    public Translate getAreaPoint(){
        return model.getAreaPoint();
    }

    String getId() {
        return model.getId();
    }



    public void changeCombiOwner(CombinationController combi){
        removeOwner();
        model.setOwningCombi(combi);
    }

    public void removeOwner(){
        if(model.getOwningCombi()!= null) model.getOwningCombi().removeArea(this);
    }

    public PlayerController getOwnerPlayer() {
        return getOwnerCombi().getPlayer();
    }

    public CombinationController getOwnerCombi(){
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

    public String getSpecialProp() {
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

    public boolean isBorderArea() {
        return model.firstAttackArea();
    }

    public boolean isAttackAble() {
        return model.isAttackAble();
    }

    List<String> getNeighbours() {
        return model.getNeigbours();
    }

    public void hoverEntered() {
        model.changeHoverState();
    }

    public void hoverExited() {
        model.changeHoverState();
    }

    public int getDefenceValue() {
        int value = 2;
        for(FicheController fiche : model.getFiches()){
            value += fiche.getDefenceValue();
        }
        return value;
    }

    public void createNumber(Group group){
        numberCon = new NumberController();
        new NumberView(numberCon, group);
    }

    public void setNumber(int number){
        System.out.println("test");
        numberCon.setNumber(number);
    }

    public void createSpecialPropFiche(){
        if(!model.getSpecialProp().equals("None")) {

            if(model.getSpecialProp().equals("Magical")){
                gameCon.createCrystal().setPosition(model.getSpecialPropPoint());
            }
            else {
                Group group = gameCon.createSpecialPropFiche(model.getSpecialProp());
                int scale = 5;

                group.setRotationAxis(Rotate.Y_AXIS);
                group.setRotate(ThreadLocalRandom.current().nextInt(0, 360 + 1));

                group.setTranslateX(model.getSpecialPropPoint().getX());
                group.setTranslateY(model.getSpecialPropPoint().getY());
                group.setTranslateZ(model.getSpecialPropPoint().getZ());
                group.setScaleX(scale);
                group.setScaleY(scale);
                group.setScaleZ(scale);
            }
        }
    }

    public void showInfo() {
        System.out.println("showing: " + getAreaType());
        gameCon.getAreaInfoCon().putAreaInformationScreen(this);
    }

    public void setAreaPoint(Translate translate) {
        model.areaPoint = translate;
    }

    public void setPropPoint(Translate translate) {
        model.specialPropPoint = translate;
        createSpecialPropFiche();
    }
}
