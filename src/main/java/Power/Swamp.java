package Power;

import Controller.CombinationController;
import Enums.AreaType;
import Points.AreaTypePoints;

public class Swamp implements Power {

    @Override
    public String getName() {
        return "swamp";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {
        combiCon.setPowerPoints(new AreaTypePoints(AreaType.swamp));
    }
}
