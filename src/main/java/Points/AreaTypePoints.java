package Points;

import Controller.AreaController;
import Controller.CombinationController;
import Enums.AreaType;
import Model.CombinationModel;

public class AreaTypePoints implements Points {

    private AreaType type;
    public AreaTypePoints(AreaType type){
        this.type = type;
    }

    @Override
    public int getPoints(CombinationModel combi) {
        int count = 0;
        for(AreaController area : combi.areas){
            if(area.getAreaType() == type) count++;
        }
        return count;
    }
}
