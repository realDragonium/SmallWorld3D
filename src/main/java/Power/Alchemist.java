package Power;

import Model.CombinationModel;
import Special.RedeployPhase.AlchemistAction;

public class Alchemist implements Power {

    @Override
    public String getName() {
        return "alchemist";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.raceSpecialAction = new AlchemistAction();
    }

}
