package Power;

import Controller.CombinationController;

public class Forest implements Power {

    @Override
    public String getName() {
        return "forest";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
