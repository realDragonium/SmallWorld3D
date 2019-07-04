package Power;

import Attacks.AreaTypeAttackBoost;
import Enums.AreaType;
import Model.CombinationModel;

import java.util.ArrayList;

public class Mounted implements Power {

    @Override
    public String getName() {
        return "mounted";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        ArrayList<AreaType> list = new ArrayList<>();
        list.add(AreaType.farm);
        list.add(AreaType.hill);
        combi.powerAttackBoost = new AreaTypeAttackBoost(list);
    }

}
