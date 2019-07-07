package Phase;

import Controller.CombinationController;
import Controller.PhaseController;
import Enums.PhaseEnum;

public class Conquering implements Phase{

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.REDEPLOYING);
        phaseCon.changeView();
        phaseCon.useRedeployingPowers();
    }

    @Override
    public String getName() {
        return "Conquering";
    }

    @Override
    public void setViews(CombinationController combi) {
        combi.checkAttackableAreas();
    }

    @Override
    public int getTime() {
        return 120;
    }

    @Override
    public boolean myTurn() {
        return true;
    }

}
