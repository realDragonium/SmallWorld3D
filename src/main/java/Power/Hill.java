package Power;

import Controller.CombinationController;
import Enums.AreaType;
import Model.CombinationModel;
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
    public void activatePower(CombinationModel combi) {
        combi.powerPoints = new AreaTypePoints(AreaType.hill);
    }
}
