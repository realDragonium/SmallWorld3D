package Controller;

import Model.CombinationModel;
import Objects.PowerOld;
import Observer.CombinationObserver;

import java.util.Stack;


public class CombinationController {

    private RaceController race;
    private PowerOld powerOld;
    private PlayerController player;
    private CombinationModel model;

    public void registerObserver(CombinationObserver obs){
        model.register(obs);
    }

    boolean isActive(){
        return model.isActive();
    }

    public CombinationController(RaceController race, PowerOld powerOld){
        this.race = race;
        this.powerOld = powerOld;
        powerOld.setCombiCon(this);
        model = new CombinationModel(race.getId(), powerOld.getId());
    }

    public CombinationController(String race, String power){
        model = new CombinationModel(race, power);
    }


    public void addArea(AreaController area){
        model.addArea(area);
    }

    public void attackThisArea(AreaController area){
        model.getAttack().Attack(area, this);
    }

    public Stack<FicheController> getFiches(int count){
        Stack<FicheController> tempFiches = model.removeFiches(count);
        return tempFiches;
    }


    public void setPlayer(PlayerController player){
        this.player = player;
    }

    public PlayerController getPlayer(){
        return this.player;
    }


    public RaceController getRace(){
        return race;
    }

    public PowerOld getPowerOld(){
        return powerOld;
    }


    void setToNonActive() {
        model.setToNonActive();
    }
}
