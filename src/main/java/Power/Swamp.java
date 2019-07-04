package Power;

import Enums.AreaType;
import Model.CombinationModel;
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
    public void activatePower(CombinationModel combi) {
        combi.powerPoints = new AreaTypePoints(AreaType.swamp);
    }

}
