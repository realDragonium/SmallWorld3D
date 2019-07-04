package Attacks;

import Controller.AreaController;
import Enums.AreaType;

import java.util.List;

public class AreaTypeAttackBoost implements AttackBoost {

    private List<AreaType> type;

    public AreaTypeAttackBoost(List<AreaType> type){
        this.type = type;
    }

    @Override
    public int getBoost(AreaController area) {
        if(type.contains(area.getAreaType())) return -1;
        return 0;
    }
}
