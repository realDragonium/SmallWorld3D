package Power;

import Controller.CombinationController;

public class Swamp implements Power {

    @Override
    public String getName() {
        return "swamp";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
