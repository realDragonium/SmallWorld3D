package Points;

import Controller.AreaController;
import Controller.CombinationController;
import Enums.AreaProperty;
import Model.CombinationModel;

public class SpecialPropertyPoints implements Points {

    private AreaProperty prop;

    public SpecialPropertyPoints(AreaProperty prop){
        this.prop = prop;
    }

    @Override
    public int getPoints(CombinationModel combi) {
        int count = 0;
        for(AreaController area : combi.areas){
            if(area.getSpecialProp() == prop) count++;
        }
        return count;
    }
}
