package Phase;

import Controller.CombinationController;
import Controller.GameController;
import Controller.PhaseController;
import Enums.PhaseEnum;


public class Preparing implements Phase{

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.CONQUERING);
        phaseCon.changeView();
    }

    @Override
    public String getName() {
        return "Preparing";
    }

    @Override
    public void setViews(CombinationController combi) {
        if(combi == combi.getPlayer().getCurrentCombi())
            combi.showDeclineMessage();
        else{
            combi.prepareRound();
            combi.checkPrepareAreas();
        }
    }


    @Override
    public int getTime() {
        return 30;
    }

    @Override
    public boolean myTurn() {
        return true;
    }
}
