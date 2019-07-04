package Power;

import Controller.CombinationController;

public class Alchemist implements Power {

    @Override
    public String getName() {
        return "alchemist";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
