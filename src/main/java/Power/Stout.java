package Power;

import Controller.CombinationController;

public class Stout implements Power {

    @Override
    public String getName() {
        return "stout";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
