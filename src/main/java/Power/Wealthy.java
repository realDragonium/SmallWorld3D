package Power;

import Controller.CombinationController;

public class Wealthy implements Power {

    @Override
    public String getName() {
        return "wealthy";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {
        combiCon.getPlayer().addPoints(7);
    }
}
