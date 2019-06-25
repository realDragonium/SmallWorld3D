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
        System.out.println("ratten hebben geen kracht maar hebben genoeg aan hun aantallen");
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return false;
    }
}
