package Power;

import Attacks.FlyAttack;
import Controller.CombinationController;
import Model.CombinationModel;

public class Flying implements Power {

    @Override
    public String getName() {
        return "flying";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.powerAreas = new FlyAttack();
    }

}
