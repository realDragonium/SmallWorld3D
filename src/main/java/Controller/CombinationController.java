package Controller;

import Attacks.AttackableAreas;
import Attacks.AttackableType;
import Enums.AreaInfoEnum;
import Enums.AreaType;
import Model.CombinationModel;
import Objects.SpecialFXMLLoader;
import Observer.CombinationObserver;
import Phase.Phase;
import Points.Points;
import Power.EveryRoundPower;
import View.CombinationView;
import javafx.scene.transform.Translate;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;

public class CombinationController {

    private PlayerController player;
    private CombinationModel model;
    private GameController gameCon;


    public CombinationController(GameController gameCon, String race, String power) {
        model = new CombinationModel(race, power);
        createCombinationView();
        this.gameCon = gameCon;
    }

    public void setPowersActive(){
        model.getPower().activatePower(this);
        model.getRace().activateRacePower(this);
    }

    private void createCombinationView() {
        new SpecialFXMLLoader().loader("/CombinationView.fxml", (Callable<CombinationView>) () -> new CombinationView(this));
    }

    public void registerObserver(CombinationObserver obs) {
        model.register(obs);
    }

    boolean isActive() {
        return model.isActive();
    }

    Phase getStartingPhase() {
        return model.getStartingPhase();
    }

    public void addArea(AreaController area) {
        model.addArea(area);
        model.thisRoundConquered.add(area);
    }

    public void removeArea(AreaController area) {
        model.removeArea(area);
    }

    public List<AreaController> getAreas() {
        return model.getAreas();
    }

    public void attackThisArea(AreaController area) {
        model.getAttack().Attack(area, this);
        model.nextAttack();
    }

    public void leaveArea(AreaController area) {
        removeArea(area);
        area.leaveArea();
        player.addPoints(-1);
    }

    public Stack<FicheController> getFiches(int count) {
        return model.removeFiches(count);
    }

    public void addRaceFiche(FicheController fiche) {
        Translate fichePos = new Translate(player.get3dPos().getX(), (player.get3dPos().getY() + ((model.getFichesAmount() - 1) * 10)), player.get3dPos().getZ());
        model.addFiche(fiche);
        fiche.moveToPosition(fichePos);
    }

    public int getFichesAmount() {
        return model.getFichesAmount();
    }

    public void setPlayer(PlayerController player) {
        this.player = player;
    }

    public PlayerController getPlayer() {
        return this.player;
    }

    public String getRace() {
        return model.getRaceId();
    }

    public String getPower() {
        return model.getPowerId();
    }

    public void setAttackType(AttackableAreas type){
        model.setAttack(type);
    }

    public void setAttackableType(AttackableType type) {
        model.setAttackableType(type);
    }

    public List<AreaType> getAttackableTypes(){
        return model.getAttackableAreaTypes();
    }

    void goIntoDecline() {
        model.goIntoDecline();
        getPlayer().setDeclineCombi(this);
    }

    void moveToPosition(Translate pos) {
        model.setPosition(pos);
    }

    void createRaceFiches() {
        int fiches = model.getRace().getFicheAmount() + model.getPower().getFicheAmount();
        for (int i = 0; i < fiches; i++) {
            FicheController ficheCon = new FicheController(1, this);
            addRaceFiche(ficheCon);
        }
    }

    FicheController getFiche() {
        return getFiches(1).pop();
    }

    public void clickedCombination() {
        gameCon.showCombinationInfo(this);
    }

    public void buyItem(){
        if (model.inShop) {
            int item = gameCon.getShopCon().getShopItem(this);
            gameCon.getShopCon().buyToFirebase(item);
            model.inShop = false;
        }
    }

    public void cleareAreaInfo(){
        model.lastUsedAreas.forEach(area -> area.setAreaInfoButton(AreaInfoEnum.NONE));
    }

    public void checkRedeployAreas() {
        manageAreaInfoButtons(model.getAreas(), AreaInfoEnum.REDEPLOY);
    }

    public void checkAttackableAreas() {
        List<AreaController> areas;
        areas = model.getAttack().checkAttackableAreas(this, gameCon.getMapCon().getAllAreas());
        manageAreaInfoButtons(areas, AreaInfoEnum.ATTACK);
    }

    public void checkPrepareAreas() {
        manageAreaInfoButtons(model.getAreas(), AreaInfoEnum.LEAVE);
    }

    private void manageAreaInfoButtons(List<AreaController> areas, AreaInfoEnum areainfo){
        model.lastUsedAreas.removeAll(areas);
        model.lastUsedAreas.forEach(area -> area.setAreaInfoButton(AreaInfoEnum.NONE));
        areas.forEach(area -> area.setAreaInfoButton(areainfo));
        model.lastUsedAreas = areas;
    }

    public void setEveryRoundPower(EveryRoundPower power){
        model.everyRoundPower = power;
    }

    public void doEveryRoundPower(){

    }

    void countPoints() {
        int totalPoints = model.getPointCounter().getPoints(this);
        totalPoints += getExtraPoints();
        player.addPoints(totalPoints);
    }

    public void setRacePoints(Points points){
        model.racePoints = points;
    }

    public void setPowerPoints(Points points){
        model.powerPoints = points;
    }

    private int getExtraPoints(){
        return model.racePoints.getPoints(this) + model.powerPoints.getPoints(this);
    }

}
