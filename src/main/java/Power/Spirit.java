package Power;

import Controller.CombinationController;

public class Spirit implements Power {

    @Override
    public String getName() {
        return "spirit";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
