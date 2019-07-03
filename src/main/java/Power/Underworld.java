package Power;

import Controller.CombinationController;

public class Underworld implements Power {

    @Override
    public String getName() {
        return "underworld";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
