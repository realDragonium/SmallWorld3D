package Power;

import Controller.CombinationController;

public class Berserk implements Power {

    @Override
    public String getName() {
        return "berserk";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
