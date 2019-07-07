package Power;

import Model.CombinationModel;
import Special.RedeployPhase.StoutAction;

public class Stout implements Power {

    @Override
    public String getName() {
        return "stout";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.powerSpecialAction = new StoutAction();
    }

}
