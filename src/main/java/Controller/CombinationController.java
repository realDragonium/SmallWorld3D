package Controller;

import Enum.TurnFase;
import Fiches.RaceFiche;
import Model.CombinationModel;
import Objects.PowerOld;
import Observer.CombinationObserver;
import Enum.RaceEnum;
import Enum.PowerEnum;
import Race.Race;
import Power.Power;

import java.util.Stack;


public class CombinationController {

    private RaceController race;
    private PowerOld powerOld;
    private PlayerController player;
    private CombinationModel model;

    private Race race2;
    private Power power2;

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
        //SceneManager.getInstance().loadCombination(this);
    }

    public CombinationController(){}

    public CombinationController(String race, String power){
        this.race2 = RaceEnum.valueOf(race).getRace();
        this.power2 = PowerEnum.valueOf(power).getPower();

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

    void checkForSpecialActions(TurnFase curPhase){
        if(race.checkPhaseActoin(curPhase)){
            race.doKractAction();
        }
        if(powerOld.checkPhaseAction(curPhase)){
            powerOld.doAction();
        }
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
