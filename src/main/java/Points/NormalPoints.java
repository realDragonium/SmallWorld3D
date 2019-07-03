package Points;

import Controller.CombinationController;
import Enums.AreaType;

import java.util.HashMap;
import java.util.Map;

public class NormalPoints implements Points {


    @Override
    public int getPoints(CombinationController combi) {
        return combi.getAreas().size();
    }
}
