package Phase;

import Controller.CombinationController;
import Controller.PhaseController;

public interface Phase {
    void nextPhase(PhaseController phaseCon);
    String getName();
    void setViews(CombinationController combi);
    int getTime();

    boolean myTurn();
}
