package Power;

import Controller.CombinationController;

public class Commando implements Power {

    @Override
    public String getName() {
        return "commando";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
