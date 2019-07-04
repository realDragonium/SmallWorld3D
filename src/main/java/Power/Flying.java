package Power;

import Attacks.FlyAttack;
import Controller.CombinationController;

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
    public void activatePower(CombinationController combiCon) {
        combiCon.setAttackType(new FlyAttack());
    }
}
