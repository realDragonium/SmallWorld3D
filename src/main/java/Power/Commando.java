package Power;

import Attacks.CommandoAttackBoost;
import Model.CombinationModel;

public class Commando implements Power {

    @Override
    public String getName() {
        return "commando";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.powerAttackBoost = new CommandoAttackBoost();
    }

}
