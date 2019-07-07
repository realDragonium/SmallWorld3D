package Power;

import Model.CombinationModel;
import Special.BerserkAction;

public class Berserk implements Power, SpecialPower {

    @Override
    public String getName() {
        return "berserk";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.powerSpecialAction = new BerserkAction();
    }

}
