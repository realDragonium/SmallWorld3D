package Phase;

import Controller.CombinationController;
import Controller.GameController;
import Controller.PhaseController;
import Enums.PhaseEnum;

public class SpectateConquering implements Phase {


    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.SPECTATEREDEPLOYING);
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
        return 20;
    }

    @Override
    public boolean myTurn() {
        return false;
    }

}
