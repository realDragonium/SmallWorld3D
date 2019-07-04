package Power;

import Model.CombinationModel;
import Points.NormalPoints;

public class Merchant implements Power {

    @Override
    public String getName() {
        return "merchant";
    }

    @Override
    public int getFicheAmount() {
        return 2;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.powerPoints = new NormalPoints();
    }
}
