package Power;

import Model.CombinationModel;

public class Alchemist implements Power, EveryRoundPower {

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
        combi.everyRoundPower = this;
    }

    @Override
    public void doAction(CombinationModel combi) {
        combi.player.addPoints(2);
    }
}
