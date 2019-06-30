package Turn;

import Controller.PhaseController;

public class MyTurn implements Turn {

    @Override
    public void nextTurn(PhaseController phaseCon) {
        phaseCon.myTurn();
    }
}
