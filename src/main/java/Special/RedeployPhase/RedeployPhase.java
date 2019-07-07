package Special.RedeployPhase;

import Controller.CombinationController;
import Controller.GameController;
import Model.CombinationModel;

public interface RedeployPhase {
    void doAction(GameController gameCon, CombinationController combi);

}
