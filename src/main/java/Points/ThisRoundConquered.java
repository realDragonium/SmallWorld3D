package Points;

import Model.CombinationModel;

public class ThisRoundConquered implements Points {

    @Override
    public int getPoints(CombinationModel combi) {
        return combi.thisRoundConquered.size();
    }
}
