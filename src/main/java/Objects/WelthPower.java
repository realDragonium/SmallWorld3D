package Objects;

import Controller.CombinationController;
import Enum.TurnFase;

public class WelthPower implements Power {

    private String id = "Welth";
    private boolean used = false;
    private TurnFase usablePhase = TurnFase.redeploying;
    private CombinationController combiCon;

    @Override
    public void doAction(){
        if(!used){
            combiCon.getPlayer().addPoints(7);
            used = true;
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean checkPhaseAction(TurnFase phase) {
        return phase.equals(usablePhase);
    }

    @Override
    public void setCombiCon(CombinationController combiCon) {
        this.combiCon = combiCon;
    }
}
