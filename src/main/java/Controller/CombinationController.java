package Controller;

import Enum.TurnFase;
import Model.CombinationModel;
import Objects.Power;
import Observer.CombinationObserver;

public class CombinationController {

    private RaceController race;
    private Power power;
    private PlayerController player;
    private CombinationModel model;

    public void registerObserver(CombinationObserver obs){
        model.register(obs);
    }

    boolean isActive(){
        return model.isActive();
    }

    public CombinationController(RaceController race, Power power){
        this.race = race;
        this.power = power;
        power.setCombiCon(this);
        model = new CombinationModel(race.getId(), power.getId());
        //SceneManager.getInstance().loadCombination(this);
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
        if(power.checkPhaseAction(curPhase)){
            power.doAction();
        }
    }

    public RaceController getRace(){
        return race;
    }

    public Power getPower(){
        return power;
    }



    void returnFiches() {
            race.returnFiches();
    }

    void setToNonActive() {
        model.setToNonActive();
        for(AreaController area: getRace().getAllAreas()){
            area.destroyAllButOne();
        }
    }

    void destroyAllFichesButOne() {
        race.destroyAllFichesButOne();
    }
}
