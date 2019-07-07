package Special.RedeployPhase;

import Controller.CombinationController;
import Controller.GameController;
import Special.SpecialAction;

public class AlchemistAction implements SpecialAction, RedeployPhase {

    @Override
    public void doAction(GameController gameCon, CombinationController combi) {
        combi.getPlayer().addPoints(2);
    }
}
