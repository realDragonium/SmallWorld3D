package Power;

import Controller.CombinationController;

public class Pillaging implements Power {

    @Override
    public String getName() {
        return "pillaging";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
