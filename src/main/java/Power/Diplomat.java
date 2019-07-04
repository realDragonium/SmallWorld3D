package Power;

import Model.CombinationModel;

public class Diplomat implements Power {

    @Override
    public String getName() {
        return "diplomat";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationModel combi) {

    }

}
