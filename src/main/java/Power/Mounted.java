package Power;

import Controller.CombinationController;

public class Mounted implements Power {

    @Override
    public String getName() {
        return "mounted";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
