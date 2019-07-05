package Turn;

import Controller.CombinationController;
import Controller.PhaseController;
import Controller.PlayerController;

public class MyTurn implements Turn {

    private final PlayerController player;
    private final CombinationController combi;

    public MyTurn(PlayerController player,CombinationController combi ) {
        this.combi = combi;
        this.player = player;
    }


    @Override
    public void nextTurn(PhaseController phaseCon) {
        if(combi.isActive()) phaseCon.myTurn();
        else phaseCon.nextTurn();
    }

    @Override
    public PlayerController getPlayer() {
        return player;
    }

    @Override
    public CombinationController getCombi() {
        return combi;
    }
}
