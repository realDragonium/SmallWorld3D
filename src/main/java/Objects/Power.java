package Objects;

import Controller.CombinationController;
import Enum.TurnFase;

/**
 * This interface is the Power which is implemented by
 * @author Yoran de Vos
 * @version June 2019
 */
public interface Power {


    void doAction();

    /**
     * @return a String of an id
     */
    String getId();

    /**
     * @param phase is a parameter of the type TurnFase
     * @return a Boolean that checks whether the phase is true or false
     */
    boolean checkPhaseAction(TurnFase phase);

    /**
     * @param combinationController is a parameter of the type CombinationController
     */

    void setCombiCon(CombinationController combinationController);
}
