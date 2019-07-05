package Controller;

import Decline.InDecline;
import Enums.AreaInfoEnum;
import Enums.NotificationEnum;
import Model.CombinationModel;
import Objects.SpecialFXMLLoader;
import Observer.CombinationObserver;
import View.CombinationView;
import javafx.application.Platform;
import javafx.scene.transform.Translate;

import java.util.*;
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

    private void attack(AreaController area, int number) {
        area.attackArea(getFiches(number));
        area.changeCombiOwner(this);
        addArea(area);
    }

    void retreat(AreaController area) {
        removeArea(area);
        model.defend.retreat(this);
    }


    void diceAttackThisArea(AreaController area, int eyes) {
        int needed = fichesNeeded(area) - eyes;
        if (!(needed <= model.raceFiches.size())) {
            gameCon.setMessage(NotificationEnum.DICENOTENOUGH);
            return;
        }
        if (needed < 1) needed = 1;
        int number = needed;
        TimerTask hide = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    attack(area, number);
                });
            }
        };
        new Timer().schedule(hide, 3250);
    }

    int fichesNeeded(AreaController area) {
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
        return model.removeFiches(count);
    }

    void addRaceFiche(FicheController fiche) {
        Translate fichePos = new Translate(player.get3dPos().getX(), (player.get3dPos().getY() + ((getFichesAmount() - 1) * 10)), player.get3dPos().getZ());
        model.raceFiches.add(fiche);
        fiche.moveToPosition(fichePos);
    }

    int getFichesAmount() {
        return model.raceFiches.size();
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
        model.decline = new InDecline();
        getPlayer().setDeclineCombi(this);
        keepOneFichePerArea();
        int times = model.raceFiches.size();
        for (int i = 0; i < times; i++)
            fichePoof();

    }

    void moveToPosition(Translate pos) {
        model.setPosition(pos);
    }

    void createRaceFiches() {
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
        manageAreaInfoButtons(model.getAreas(), AreaInfoEnum.REDEPLOY);
    }

    public void checkAttackableAreas() {
        HashSet<AreaController> areas;
        areas = new HashSet<>(model.attackableAreas.checkAttackableAreas(model, gameCon.getMapCon().getAllAreas()));
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
        model.everyRoundPower.doAction(model);
        int totalPoints = model.points.getPoints(model);
        totalPoints += model.racePoints.getPoints(model);
        totalPoints += model.powerPoints.getPoints(model);
        player.addPoints(totalPoints);
    }

    public void returnAllButOne(AreaController area) {
        for (int i = 0; i < (area.getFichesAmount() - 1); i++) {
            addRaceFiche(area.removeFiche());
        }
    }


    private void keepOneFichePerArea() {
        model.getAreas().forEach(area -> returnAllButOne(area));
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
}
