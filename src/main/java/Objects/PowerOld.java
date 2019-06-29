package Objects;

import Controller.CombinationController;
import Enum.TurnFase;

public interface PowerOld {

    void doAction();

    String getId();

    boolean checkPhaseAction(TurnFase phase);

    void setCombiCon(CombinationController combinationController);
}
