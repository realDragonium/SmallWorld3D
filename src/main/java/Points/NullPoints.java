package Points;

import Controller.CombinationController;

public class NullPoints implements Points {
    @Override
    public int getPoints(CombinationController combi) {
        return 0;
    }
}
