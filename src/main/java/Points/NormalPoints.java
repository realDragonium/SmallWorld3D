package Points;

import Model.CombinationModel;

public class NormalPoints implements Points {

    @Override
    public int getPoints(CombinationModel combi) {
        return combi.areas.size();
    }
}
