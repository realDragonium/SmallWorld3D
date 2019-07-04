package Points;

import Controller.AreaController;
import Controller.CombinationController;
import Enums.AreaType;

public class AreaTypePoints implements Points {

    private AreaType type;
    public AreaTypePoints(AreaType type){
        this.type = type;
    }

    @Override
    public int getPoints(CombinationController combi) {
        int count = 0;
        for(AreaController area : combi.getAreas()){
            if(area.getAreaType() == type) count++;
        }
        return count;
    }
}
