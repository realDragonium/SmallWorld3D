package Objects;

import Controller.CombinationController;
import Enum.TurnFase;

public class AlchemistPowerOld implements PowerOld {


    private TurnFase usingPhase = TurnFase.redeploying;
    private String id = "Alchemist";
    private CombinationController combiCon;

    @Override
    public void doAction(){
        combiCon.getPlayer().addPoints(2);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean checkPhaseAction(TurnFase phase) {
        return phase.equals(usingPhase);
    }

    @Override
    public void setCombiCon(CombinationController combiCon) {
        this.combiCon = combiCon;
    }
}
