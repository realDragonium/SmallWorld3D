package Objects;

import Controller.RaceController;
import Enum.TurnFase;

/**
 * This interface is the kracht which is implemented by
 * @author Yoran de Vos
 * @version June 2019
 */
public interface Kracht {

    /**
     * @param raceCon is a parameter of RaceController
     */
    void setRaceCon(RaceController raceCon);


    Kracht getKracht();

    void doAction();

    /**
     * @param curPhase is of the type TurnFase
     * @return a boolean to check whether the checkPhaseAction is true or false.
     */
    boolean checkPhaseAction(TurnFase curPhase);
}
