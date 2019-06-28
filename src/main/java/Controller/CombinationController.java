package Controller;

import Enum.TurnFase;
import Model.CombinationModel;
import Objects.PowerOld;
import Observer.CombinationObserver;
import Enum.RaceEnum;
import Enum.PowerEnum;
import Race.Race;
import Power.Power;


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

    public Power getPower2() {
        return power2;
    }

    public Race getRace2(){
        return race2;
    }

    public String getRaceName(){
        return race.getId();
    }

    public String getPowerName(){
        return powerOld.getId();
    }

    public void setPlayer(PlayerController player){
        this.player = player;
    }

    public PlayerController gatPlayer(){
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

    public RaceController gatRace(){
        return race;
    }

    public PowerOld gatPowerOld(){
        return powerOld;
    }



    void returnFiches() {
            race.returnFiches();
    }

    void setToNonActive() {
        model.setToNonActive();
        for(AreaController area: gatRace().getAllAreas()){
            area.destroyAllButOne();
        }
    }

    void destroyAllFichesButOne() {
        race.destroyAllFichesButOne();
    }
}
