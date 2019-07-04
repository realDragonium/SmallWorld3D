package Power;

import Controller.CombinationController;

public class Merchant implements Power {

    @Override
    public String getName() {
        return "merchant";
    }

    @Override
    public int getFicheAmount() {
        return 2;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
