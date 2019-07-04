package Power;

import Model.CombinationModel;

public class Fortified implements Power {

    @Override
    public String getName() {
        return "fortified";
    }

    @Override
    public int getFicheAmount() {
        return 3;
    }

    @Override
    public void activatePower(CombinationModel combi) {

    }

}
