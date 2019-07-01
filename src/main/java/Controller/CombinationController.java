package Controller;

import Attacks.AttackType;
import Model.CombinationModel;
import Observer.CombinationObserver;
import javafx.scene.transform.Translate;

import java.util.Stack;


public class CombinationController {

    private PlayerController player;
    private CombinationModel model;


    public CombinationController(String race, String power){
        model = new CombinationModel(race, power);
    }


    public void registerObserver(CombinationObserver obs){
        model.register(obs);
    }

    boolean isActive(){
        return model.isActive();
    }


    public void addArea(AreaController area){
        model.addArea(area);
    }

    public void removeArea(AreaController area) {model.removeArea(area);}

    public void attackThisArea(AreaController area){
        model.getAttack().Attack(area, this);
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
    }

    public void moveToPosition(Translate pos) {
        model.setPosition(pos);
    }

    public void createRaceFiches(){
        int fiches = model.getRace().getFicheAmount() + model.getPower().getFicheAmount();
        for(int i = 0; i < fiches; i++){
            FicheController ficheCon = new FicheController(1, model.getRaceId());
            player.getGameCon().createRaceFiche(ficheCon);
            addRaceFiche(ficheCon);
        }
    }

    public void setAttackType(AttackType attack) {
        model.setAttackType(attack);
    }
}
