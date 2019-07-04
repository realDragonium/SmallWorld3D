package Power;

import Model.CombinationModel;

public class Wealthy implements Power {

    @Override
    public String getName() {
        return "wealthy";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.player.addPoints(7);
    }

}
