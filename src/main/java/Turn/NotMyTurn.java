package Turn;

import Controller.PhaseController;

public class NotMyTurn implements Turn {
    @Override
    public void nextTurn(PhaseController phaseCon) {
        phaseCon.notMyTurn();
    }
}
