package Phase;

import Controller.CombinationController;
import Controller.GameController;
import Controller.PhaseController;
import Enums.PhaseEnum;


public class Preparing implements Phase{

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.CONQUERING);
    }

    @Override
    public String getName() {
        return "Preparing";
    }

    @Override
    public void setViews(CombinationController combi) {
        combi.checkPrepareAreas();
    }
}
