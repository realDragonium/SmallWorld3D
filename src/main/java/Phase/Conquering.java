package Phase;

import Controller.CombinationController;
import Controller.PhaseController;
import Enums.PhaseEnum;

public class Conquering implements Phase{

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.REDEPLOYING);
    }


    @Override
    public String getName() {
        return "Conquering";
    }

    @Override
    public void setViews(CombinationController combi) {
        combi.checkAttackableAreas();
    }

}
