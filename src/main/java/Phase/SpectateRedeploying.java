package Phase;

import Controller.CombinationController;
import Controller.GameController;
import Controller.PhaseController;

public class SpectateRedeploying implements Phase {


    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.nextTurn();
    }

    @Override
    public String getName() {
        return "Redeploying";
    }

    @Override
    public void setViews(CombinationController combi) {

    }
}
