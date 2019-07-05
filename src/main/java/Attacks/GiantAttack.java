package Attacks;

import Controller.AreaController;
import Enums.AreaType;
import Model.CombinationModel;

public class GiantAttack implements AttackBoost {

    private CombinationModel model;
    public GiantAttack(CombinationModel model){
        this.model = model;
    }

    @Override
    public int getBoost(AreaController area) {
        for(AreaController are: model.areas) {
            if(are.getAreaType() == AreaType.mountain){
                if(are.getNeighbourCons().contains(area)) return -1;
            }
        }
        return 0;
    }
}
