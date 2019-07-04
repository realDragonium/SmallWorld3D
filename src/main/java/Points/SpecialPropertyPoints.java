package Points;

import Controller.AreaController;
import Controller.CombinationController;
import Enums.AreaProperty;

public class SpecialPropertyPoints implements Points {

    private AreaProperty prop;

    public SpecialPropertyPoints(AreaProperty prop){
        this.prop = prop;
    }

    @Override
    public int getPoints(CombinationController combi) {
        int count = 0;
        for(AreaController area : combi.getAreas()){
            if(area.getSpecialProp() == prop) count++;
        }
        return count;
    }
}
