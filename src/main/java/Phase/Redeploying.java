package Phase;
import Controller.CombinationController;
import Controller.GameController;
import Controller.PhaseController;


public class Redeploying implements Phase {

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.clearAreaInfoView();
        phaseCon.nextTurn();
    }

    @Override
    public String getName() {
        return "Redeploying";
    }

    @Override
    public void setViews(CombinationController combi) {
        combi.checkRedeployAreas();
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
