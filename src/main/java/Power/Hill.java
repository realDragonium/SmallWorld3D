package Power;

import Controller.CombinationController;
import Enums.AreaType;
import Points.AreaTypePoints;

public class Hill implements Power {

    @Override
    public String getName() {
        return "hill";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationController combiCon) {
        combiCon.setPowerPoints(new AreaTypePoints(AreaType.hill));
    }
}
