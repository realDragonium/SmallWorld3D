package Power;

import Decline.NotInDecline;
import Model.CombinationModel;

public class Spirit implements Power {

    @Override
    public String getName() {
        return "spirit";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.inDecline = new NotInDecline();
    }
}
