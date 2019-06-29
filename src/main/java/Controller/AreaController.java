package Controller;

import Enum.AreaProperty;
import Enum.AreaType;
import Model.AreaModel;
import Fiches.RaceFiche;
import Objects.AreaInfo;
import Observer.AreaObserver;
import View.NumberView;
import javafx.scene.Group;
import javafx.scene.transform.Translate;

import java.util.List;
import java.util.Stack;

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

//        getAreaInfo(id);
    }

    public AreaController(AreaInfo info, MapController map,  GameController gameCon){
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
    public void addFiche(){}
    public void removeFiche(){}
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////ALLEEN DEZE TWEE GEBRUIKEN VOOR AANVALLEN///////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////



    public void createFiche(){
        FicheController fiche = map3DCon.con3D.createRaceFiche("ratten");
        map3DCon.placeFiche(this, fiche);
        //fiche.moveToPosition(model.getAreaPoint());
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

    public void attackArea(Stack<FicheController> fiches) {
        model.setFiches(fiches);
//        fb.areaUpdateFiches(model.getId(), model.getNumberOfFiches());
    }

    public void setPlayerOwner(PlayerController player) {
        model.player = player;
    }

    public PlayerController getOwnerPlayer() {
        return model.player;
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

    boolean firstAttackArea() {
        return model.firstAttackArea();
    }

    boolean isAttackAble() {
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
        int value = 0;
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
        numberCon.setNumber(number);
    }

    public void showInfo() {
        gameCon.getAreaInfoCon().putAreaInformationScreen(this);
    }
}
