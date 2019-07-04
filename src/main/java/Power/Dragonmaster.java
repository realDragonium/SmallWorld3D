package Power;

import Model.CombinationModel;

public class Dragonmaster implements Power {

    @Override
    public String getName() {
        return "dragonmaster";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationModel combi) {

    }

}
