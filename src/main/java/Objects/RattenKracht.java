package Objects;

import Controller.RaceController;
import Enum.TurnFase;

public class RattenKracht implements Kracht{

    @Override
    public void setRaceCon(RaceController raceCon) {

    }

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {

    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return false;
    }
}
