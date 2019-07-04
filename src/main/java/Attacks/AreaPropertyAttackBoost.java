package Attacks;

import Controller.AreaController;
import Enums.AreaProperty;

public class AreaPropertyAttackBoost implements  AttackBoost {
    private AreaProperty areaProperty;

    public AreaPropertyAttackBoost(AreaProperty prop){
        areaProperty = prop;
    }

    @Override
    public int getBoost(AreaController area) {
        if(area.getSpecialProp() == areaProperty) return -1;
        return 0;
    }
}
