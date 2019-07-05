package Turn;


import Controller.CombinationController;
import Controller.PhaseController;
import Controller.PlayerController;

public interface Turn {
    void nextTurn(PhaseController phaseCon);
    PlayerController getPlayer();
    CombinationController getCombi();
}
