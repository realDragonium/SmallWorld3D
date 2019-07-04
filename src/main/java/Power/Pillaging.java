package Power;

import Model.CombinationModel;
import Points.ThisRoundConquered;

public class Pillaging implements Power {

    @Override
    public String getName() {
        return "pillaging";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.powerPoints = new ThisRoundConquered();
    }

}
