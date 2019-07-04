package Controller;

import Attacks.AttackableType;
import Enums.AreaInfoEnum;
import Enums.AreaType;
import Model.CombinationModel;
import Objects.SpecialFXMLLoader;
import Observer.CombinationObserver;
import Phase.Phase;
import Points.Points;
import View.CombinationView;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.HashSet;
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

    public void setPowersActive() {
        model.getPower().activatePower(model);
        model.getRace().activateRacePower(model);
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

    void attackThisArea(AreaController area) {
        attack(area, this);

    }

    private void attack(AreaController area, CombinationController combi) {
        combi.addArea(area);
        area.attackArea(combi.getFiches(fichesNeeded(area)));
        area.changeCombiOwner(combi);
    }

    private int fichesNeeded(AreaController area) {
        int numbers = area.getDefenceValue();
        numbers += model.powerAttackBoost.getBoost(area) + model.raceAttackBoost.getBoost(area);
        return numbers;
    }


    void leaveArea(AreaController area) {
        removeArea(area);
        area.leaveArea();
        player.addPoints(-1);
    }

    Stack<FicheController> getFiches(int count) {
        return model.removeFiches(count);
    }

    void addRaceFiche(FicheController fiche) {
        Translate fichePos = new Translate(player.get3dPos().getX(), (player.get3dPos().getY() + ((getFichesAmount() - 1) * 10)), player.get3dPos().getZ());
        model.raceFiches.add(fiche);
        fiche.moveToPosition(fichePos);
    }

    int getFichesAmount() {
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

    void goIntoDecline() {
        model.goIntoDecline();
        getPlayer().setDeclineCombi(this);
        keepOneFichePerArea();
    }

    void moveToPosition(Translate pos) {
        model.setPosition(pos);
    }

    void createRaceFiches() {
        int fiches = model.getRace().getFicheAmount() + model.getPower().getFicheAmount();
        for (int i = 0; i < fiches; i++) {
            FicheController ficheCon = new FicheController(1, model.race.getName());
            addRaceFiche(ficheCon);
        }
    }

    FicheController getFiche() {
        return getFiches(1).pop();
    }

    public void clickedCombination() {
        if (model.inShop) {
            int item = gameCon.getShopCon().getShopItem(this);
            gameCon.getShopCon().buyToFirebase(item);
            model.inShop = false;
        }
    }

    public void cleareAreaInfo() {
        model.lastUsedAreas.forEach(AreaController::resetAreaInfo);
    }

    public void checkRedeployAreas() {
        manageAreaInfoButtons(model.getAreas(), AreaInfoEnum.REDEPLOY);
    }

    public void checkAttackableAreas() {
        HashSet<AreaController> areas;
        areas = new HashSet<>(model.getAttack().checkAttackableAreas(model, gameCon.getMapCon().getAllAreas()));
        areas.addAll(model.powerAreas.checkAttackableAreas(model, gameCon.getMapCon().getAllAreas()));
        areas.addAll(model.raceAreas.checkAttackableAreas(model, gameCon.getMapCon().getAllAreas()));

        List<AreaController> arealist = new ArrayList<>(areas);
        manageAreaInfoButtons(arealist, AreaInfoEnum.ATTACK);
    }

    public void checkPrepareAreas() {
        manageAreaInfoButtons(model.getAreas(), AreaInfoEnum.LEAVE);
    }

    private void manageAreaInfoButtons(List<AreaController> areas, AreaInfoEnum areainfo) {
        cleareAreaInfo();
        areas.forEach(area -> area.setAreaInfoButton(areainfo));
        model.lastUsedAreas = areas;
    }

    void countPoints() {
        int totalPoints = model.getPointCounter().getPoints(model);
        totalPoints +=  model.racePoints.getPoints(model);
        totalPoints += model.powerPoints.getPoints(model);
        player.addPoints(totalPoints);
    }

    public void setRacePoints(Points points) {
        model.racePoints = points;
    }


    public List<AreaType> getAttackableTypes() {
        return model.attackableType.getAttackableTypes();
    }

    public void setAttackableType(AttackableType type) {
        model.attackableType = type;
    }

    public void returnAllButOne(AreaController area) {
        for(int i = 0; i < (area.getFichesAmount() - 1); i++) {
            addRaceFiche(area.removeFiche());
        }
    }

    public void keepOneFichePerArea(){
        model.getAreas().forEach(area -> returnAllButOne(area));
    }

    public void prepareRound() {
        keepOneFichePerArea();
        model.thisRoundConquered = new ArrayList<>();
        model.everyRoundPower.doAction(model);
    }
}
