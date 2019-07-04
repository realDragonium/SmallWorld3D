package Power;

import Model.CombinationModel;

public class None implements EveryRoundPower, Power {

    @Override
    public String getName() {
        return "None";
    }

    @Override
    public int getFicheAmount() {
        return 0;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        //Do Nothing
    }

    @Override
    public void doAction(CombinationModel combi) {
        //Do Nothing
    }
}
