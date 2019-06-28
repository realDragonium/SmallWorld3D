package Controller;

import Enum.AreaProperty;
import Enum.AreaType;
import Firebase.FirebaseGameObserver;
import Model.AreaModel;
import Objects.RaceFiche;
import Observer.AreaObserver;
import View.NumberView;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.scene.Group;
import javafx.scene.transform.Translate;

import java.util.List;
import java.util.Stack;

public class AreaController implements FirebaseGameObserver {


    private Map2DController map2DCon;
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
        listenToFirebase();
    }

    public AreaController(Group area, Map2DController map2DController, GameController gameCon) {
        String id = area.getChildren().get(0).getId();
        model = new AreaModel(id);
        map2DCon = map2DController;
        this.gameCon = gameCon;
        fb = gameCon.getFireBase();

//        getAreaInfo(id);
    }

    private void listenToFirebase(){
        fb.areaListener(this);
    }

    private void loadAreaInFirebase(){
        model.setFiches(1);
        fb.areaUpdate(this);
    }

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

    int numbersNeeded() {
        return model.numbersNeeded();
    }

    void attackArea(Stack<RaceFiche> fiches) {
        model.setFiches(fiches);
//        fb.areaUpdateFiches(model.getId(), model.getNumberOfFiches());
    }

    void setPlayerOwner(PlayerController player) {
        model.player = player;
    }

    public PlayerController getOwnerPlayer() {
        return model.player;
    }

    Stack<RaceFiche> removeFiches() {
//        fb.areaUpdateFiches(model.getId(), 0);
        return model.getAllFiches();
    }

    RaceFiche getOneFiche() {
        return model.getOneFiche();
    }

    void changeActive() {
        model.changeActive();
    }

    public void register(AreaObserver ao) {
        model.register(ao);
    }

    public void selectActive() {
        map2DCon.selectSingleArea(this);
    }

    public void makeActive(){ model.changeActive();}

    public void destroyAllButOne() {
        model.getAllButOne();
//        fb.areaUpdateFiches("fiches", model.getNumberOfFiches());
    }

    void returnAllButOne(RaceController raceController) {
        raceController.pushFiches(model.getAllButOne());
//        fb.areaUpdateFiches("fiches", model.getNumberOfFiches());
    }

    private void getAreaInfo(String id) {
        model.setBorderArea(fb.isAreaBorderArea(id));
        model.setAreaType(fb.getAreaType(id));
        model.setNeighbours(fb.getNeighboursArea(id));
        model.setAttackAble(fb.isAttackable(id));
        model.setFiches(fb.basicFichesCount(id));
    }

    @Override
    public void update(DocumentSnapshot ds) {
        System.out.println(ds.getDouble("fiches").intValue());
//        if (gameCon.getCurrentPlayer() == gameCon.getMyPlayer()) return;
//        Platform.runLater(() -> {
//            if (model.getPlayer() == gameCon.getMyPlayer()) {
//                model.getPlayer().getActiveCombination().gatRace().pushFiches(removeFiches());
//                model.player = null;
//                model.setFiches(ds.getDouble("fiches").intValue());
//            }
//            model.setFiches(ds.getDouble("fiches").intValue());
//        });
    }

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
        System.out.println("setting");
        numberCon.setNumber(number);
    }

    public void showInfo() {
        gameCon.getAreaInfoCon().putAreaInformationScreen(this);
    }
}
