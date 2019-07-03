package Power;

import Attacks.SeafaringAttackableType;
import Controller.CombinationController;

public class Seafaring implements Power {

    @Override
    public String getName() {
        return "seafaring";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationController combiCon) {
        combiCon.setAttackableType(new SeafaringAttackableType());
    }
}
