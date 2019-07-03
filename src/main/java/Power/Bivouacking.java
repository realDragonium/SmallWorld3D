package Power;

import Controller.CombinationController;

public class Bivouacking implements Power {

    @Override
    public String getName() {
        return "bivouacking";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationController combiCon) {

    }
}
