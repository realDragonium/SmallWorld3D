package Phase;

import Controller.CombinationController;
import Controller.GameController;
import Controller.PhaseController;
import Enums.PhaseEnum;

public class SpectateConquering implements Phase {


    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.SPECTATEREDEPLOYING);
        phaseCon.useRedeployingPowers();
    }

    @Override
    public String getName() {
        return "Conquering";
    }

    @Override
    public void setViews(CombinationController combi) {

    }
    @Override
    public int getTime() {
        return 120;
    }

    @Override
    public boolean myTurn() {
        return false;
    }

}
