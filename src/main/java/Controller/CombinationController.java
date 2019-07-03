package Controller;

import Enums.AreaInfoEnum;
import Model.CombinationModel;
import Objects.SpecialFXMLLoader;
import Observer.CombinationObserver;
import Phase.Phase;
import View.CombinationView;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;

public class CombinationController {

    private PlayerController player;
    private CombinationModel model;
    private GameController gameCon;


    public CombinationController(GameController gameCon, String race, String power){
        model = new CombinationModel(race, power);
        createCombinationView();
        this.gameCon = gameCon;
    }

    private void createCombinationView() {
        new SpecialFXMLLoader().loader("/CombinationView.fxml", (Callable<CombinationView>)() -> new CombinationView(this));
    }

    void countPoints(){
        int totalPoints = model.getPointCounter().getPoints(this);
        player.addPoints(totalPoints);
    }

    public void registerObserver(CombinationObserver obs){
        model.register(obs);
    }

    public void checkAttackableAreas(){
        model.getAttack().checkAttackableAreas(this, gameCon.getMapCon().getAllAreas());
    }

    boolean isActive(){
        return model.isActive();
    }

    Phase getStartingPhase(){
        return model.getStartingPhase();
    }

    public void addArea(AreaController area){
        model.addArea(area);
    }

    public void removeArea(AreaController area) {model.removeArea(area);}

    public List<AreaController> getAreas(){ return model.getAreas();}

    public void attackThisArea(AreaController area){
        model.getAttack().Attack(area, this);
        model.nextAttack();
        checkAttackableAreas();
    }

    public Stack<FicheController> getFiches(int count){
        Stack<FicheController> tempFiches = model.removeFiches(count);
        return tempFiches;
    }

    public void addRaceFiche(FicheController fiche) {
        Translate fichePos = new Translate(player.get3dPos().getX(), (player.get3dPos().getY() + ((model.getFichesAmount() - 1) * 10)), player.get3dPos().getZ());
        model.addFiche(fiche);
        fiche.moveToPosition(fichePos);
    }

    public int getFichesAmount(){
        return model.getFichesAmount();
    }

    public void setPlayer(PlayerController player){
        this.player = player;
    }

    public PlayerController getPlayer(){
        return this.player;
    }

    public String getRace(){
        return model.getRaceId();
    }

    public String getPower(){return model.getPowerId();}

    void goIntoDecline() {
        model.goIntoDecline();
        getPlayer().setDeclineCombi(this);
    }

    void moveToPosition(Translate pos) {
        model.setPosition(pos);
    }

    void createRaceFiches(){
        int fiches = model.getRace().getFicheAmount() + model.getPower().getFicheAmount();
        for(int i = 0; i < fiches; i++){
            FicheController ficheCon = new FicheController(1, this);
            addRaceFiche(ficheCon);
        }
    }

    FicheController getFiche() {
        return getFiches(1).pop();
    }

    public void clickedCombination() {
        if(model.inShop){
            //if(gameCon.get.equals(player.getGameCon().getCurrentPlayer())){
                int item = gameCon.getShopCon().getShopItem(this);
                if(item != 6) gameCon.getShopCon().buyToFirebase(item);
           }
        //}
    }

    public void checkRedeployAreas() {
        List<AreaController> usingAreas = new ArrayList<>();
        List<AreaController> areas = model.getAreas();
        for(AreaController area : areas){
            if(area.getFichesAmount() > 1)
                usingAreas.add(area);
        }
        manageAreaInfoButtons(usingAreas, AreaInfoEnum.REDEPLOY);
    }

    public void checkPrepareAreas() {

    }

    private void manageAreaInfoButtons(List<AreaController> areas, AreaInfoEnum areainfo){
        model.lastUsedAreas.removeAll(areas);
        model.lastUsedAreas.forEach(area -> area.setAreaInfoButton(AreaInfoEnum.NONE));
        areas.forEach(area -> area.setAreaInfoButton(areainfo));
        model.lastUsedAreas = areas;
    }
}
