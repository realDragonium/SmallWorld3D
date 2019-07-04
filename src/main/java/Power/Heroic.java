package Power;

import Model.CombinationModel;

public class Heroic implements Power {

    @Override
    public String getName() {
        return "heroic";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationModel combi) {

    }

}
