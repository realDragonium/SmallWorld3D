package Controller;

import Enums.AreaInfoEnum;
import Enums.GameViewEnum;
import Model.CombinationModel;
import Objects.SpecialFXMLLoader;
import Observer.CombinationObserver;
import Power.SpecialPower;
import Special.AttackPhase.AttackPhase;
import Special.SpecialAction;
import View.CombinationView;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.HashSet;
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

    void setPowersActive() {
        model.power.activatePower(model);
        model.race.activateRacePower(model);
        if (model.power instanceof SpecialPower)
            gameCon.setPowerSpAtt(model.powerSpecialAction);

        model.inShop = false;
    }

    private void createCombinationView() {
        new SpecialFXMLLoader().loader("/CombinationView.fxml", (Callable<CombinationView>) () -> new CombinationView(this));
    }

    public void registerObserver(CombinationObserver obs) {
        model.register(obs);
    }

    public boolean isActive() {
        return model.decline.isActive();
    }

    private void addArea(AreaController area) {
        model.areas.add(area);
        model.thisRoundConquered.add(area);
    }

    void removeArea(AreaController area) {
        model.areas.remove(area);
    }

    void attackThisArea(AreaController area) {
        attack(area, fichesNeeded(area));
    }

    void attack(AreaController area, int number) {
        area.attackArea(getFiches(number));
        area.changeCombiOwner(this);
        addArea(area);
        model.reserve = 0;
    }

    void retreat(AreaController area) {
        removeArea(area);
        model.defend.retreat(this);
    }

    public int fichesNeeded(AreaController area) {
        int numbers = area.getDefenceValue();
        numbers += model.powerAttackBoost.getBoost(area) + model.raceAttackBoost.getBoost(area);
        if (numbers < 1) numbers = 1;
        return numbers;
    }

    boolean hasOnlyOneFiche() {
        return model.raceFiches.size() == 1;
    }

    void leaveArea(AreaController area) {
        removeArea(area);
        area.leaveArea();
        player.addPoints(-1);
    }

    private Stack<FicheController> getFiches(int count) {
        Stack<FicheController> fiches = model.removeFiches(count);
        model.player.fichesChanged();
        return fiches;
    }

    public void addRaceFiche(FicheController fiche) {
        Translate fichePos = new Translate(player.get3dPos().getX(), (player.get3dPos().getY() + ((getFichesAmount() - 1) * 10)), player.get3dPos().getZ());
        model.raceFiches.add(fiche);
        fiche.moveToPosition(fichePos);
        model.player.fichesChanged();
    }

    public int getFichesAmount() {
        return model.raceFiches.size() - model.reserve;
    }

    public void reserveFiches(int count){
        model.reserve = count;
    }

    public void setPlayer(PlayerController player) {
        this.player = player;
        model.player = player;
    }

    public PlayerController getPlayer() {
        return this.player;
    }

    public String getRaceName() {
        return model.getRaceId();
    }

    public String getPower() {
        return model.getPowerId();
    }

    void goIntoDecline() {
        model.decline = model.inDecline;
        getPlayer().setDeclineCombi(this);
        if (model.decline.isActive()) return;
        keepOneFichePerArea();
        deleteAllFichesInHand();
    }

    private void deleteAllFichesInHand() {
        int times = model.raceFiches.size();
        for (int i = 0; i < times; i++)
            fichePoof();
    }

    void moveToPosition(Translate pos) {
        model.setPosition(pos);
    }

    public void createRaceFiches() {
        int fiches = model.race.getFicheAmount() + model.power.getFicheAmount();
        for (int i = 0; i < fiches; i++) {
            FicheController ficheCon = new FicheController(1, model.race.getName());
            addRaceFiche(ficheCon);
        }
    }

    public FicheController getFiche() {
        return getFiches(1).pop();
    }

    public void clickedCombination() {
        gameCon.showCombinationInfo(this);
    }

    void buyItem() {
        ShopController shop = gameCon.getShopCon();
        int item = shop.getShopItem(this);
        shop.buyToFirebase(item);
    }

    void cleareAreaInfo() {
        model.lastUsedAreas.forEach(AreaController::resetAreaInfo);
    }

    public void checkRedeployAreas() {
        manageAreaInfoButtons(model.areas, AreaInfoEnum.REDEPLOY);
    }

    public void checkAttackableAreas() {
        HashSet<AreaController> areas;
        areas = new HashSet<>(model.attackableAreas.checkAttackableAreas(model, gameCon.getMapCon().getAllAreas()));
        areas.addAll(model.powerAreas.checkAttackableAreas(model, gameCon.getMapCon().getAllAreas()));
        areas.addAll(model.raceAreas.checkAttackableAreas(model, gameCon.getMapCon().getAllAreas()));

        manageAreaInfoButtons(areas, AreaInfoEnum.ATTACK);
        if (model.powerSpecialAction instanceof AttackPhase)
            addAreaInfoButtons(areas, AreaInfoEnum.POWERSPATT);
        if (model.raceSpecialAction instanceof AttackPhase)
            addAreaInfoButtons(areas, AreaInfoEnum.RACESPATT);
    }

    public void checkPrepareAreas() {
        manageAreaInfoButtons(model.areas, AreaInfoEnum.LEAVE);
    }

    private void manageAreaInfoButtons(HashSet<AreaController> areas, AreaInfoEnum areainfo) {
        cleareAreaInfo();
        areas.forEach(area -> area.setAreaInfoButton(areainfo));
        model.lastUsedAreas = areas;
    }

    private void addAreaInfoButtons(HashSet<AreaController> areas, AreaInfoEnum areainfo) {
        areas.forEach(area -> area.setAreaInfoButton(areainfo));
        model.lastUsedAreas.addAll(areas);
    }

    void countPoints() {
        int totalPoints = model.points.getPoints(model);
        totalPoints += model.racePoints.getPoints(model);
        totalPoints += model.powerPoints.getPoints(model);
        player.addPoints(totalPoints);
    }

    public void returnAllButOne(AreaController area) {
        int number = (area.getFichesAmount() - 1);
        for (int i = 0; i < number; i++) {
            addRaceFiche(area.removeFiche());
        }
    }


    private void keepOneFichePerArea() {
        model.areas.forEach(area -> returnAllButOne(area));
    }

    public void prepareRound() {
        keepOneFichePerArea();
        model.thisRoundConquered = new ArrayList<>();
    }

    boolean showBuyButton() {
        return model.buyButton;
    }

    void buybuttonOff() {
        model.buyButton = false;
    }

    void buybuttonOn() {
        model.buyButton = true;
    }

    public void fichePoof() {
        FicheController fiche = getFiche();
        Translate fichePoint = new Translate(0, 500, 0);
        fiche.moveToPosition(fichePoint);
    }

    void selfDestruct() {
        for (AreaController area : model.areas) {
            area.leaveArea();
        }
        deleteAllFichesInHand();
    }

    public void showDeclineMessage() {
        gameCon.addToGameView(GameViewEnum.DECLINE);
    }

    SpecialAction getPowerSpecialAction() {
        return model.powerSpecialAction;
    }

    SpecialAction getRaceSpecialAction(){
            return model.raceSpecialAction;
    }

    public int getNumberThisRoundConqueredAreas(){
        return model.thisRoundConquered.size();
    }
}
