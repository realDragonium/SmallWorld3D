package Power;

import Controller.CombinationController;

public class Hill implements Power {

    @Override
    public String getName() {
        return "hill";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
