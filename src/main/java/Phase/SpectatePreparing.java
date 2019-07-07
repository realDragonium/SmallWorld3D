package Phase;

import Controller.CombinationController;
import Controller.GameController;
import Controller.PhaseController;
import Enums.PhaseEnum;

public class SpectatePreparing implements Phase {


    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.SPECTATERECONQUERING);
    }

    @Override
    public String getName() {
        return "Preparing";
    }

    @Override
    public void setViews(CombinationController combi) {
        combi.prepareRound();
    }

    @Override
    public int getTime() {
        return 30;
    }

    @Override
    public boolean myTurn() {
        return false;
    }


}
