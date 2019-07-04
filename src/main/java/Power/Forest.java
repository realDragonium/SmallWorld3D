package Power;

import Enums.AreaType;
import Model.CombinationModel;
import Points.AreaTypePoints;

public class Forest implements Power {

    @Override
    public String getName() {
        return "forest";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.powerPoints = new AreaTypePoints(AreaType.forest);
    }
}
