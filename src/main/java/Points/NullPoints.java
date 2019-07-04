package Points;

import Controller.CombinationController;
import Model.CombinationModel;

public class NullPoints implements Points {

    @Override
    public int getPoints(CombinationModel combi) {
        return 0;
    }
}
