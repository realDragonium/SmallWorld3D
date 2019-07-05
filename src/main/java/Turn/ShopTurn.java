package Turn;

import Controller.CombinationController;
import Controller.PhaseController;
import Controller.PlayerController;

public class ShopTurn implements Turn {

    private final PlayerController player;
    private final CombinationController combi;

    public ShopTurn(PlayerController player,CombinationController combi ) {
        this.combi = combi;
        this.player = player;
    }

    @Override
    public void nextTurn(PhaseController phaseCon) {
        phaseCon.startShopTurn();
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
