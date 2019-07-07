package Special.RedeployPhase;

import Controller.CombinationController;
import Controller.GameController;
import Enums.GameViewEnum;
import Special.SpecialAction;

public class StoutAction implements SpecialAction, RedeployPhase {

    @Override
    public void doAction(GameController gameCon, CombinationController combi) {
        gameCon.addToGameView(GameViewEnum.DECLINE);
    }
}
