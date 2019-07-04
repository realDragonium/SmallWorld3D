package Attacks;

import Controller.AreaController;
import Controller.CombinationController;
import Enums.AreaType;
import Model.CombinationModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FlyAttack implements AttackableAreas {

    @Override
    public List<AreaController> checkAttackableAreas(CombinationModel combi, Collection<AreaController> allAreas) {
        List<AreaController> usableAreas = new ArrayList<>();
        List<AreaType> areaTypes = combi.attackableType.getAttackableTypes();
        allAreas.forEach(area -> {
            if (area.isAttackAble() && areaTypes.contains(area.getAreaType())) {
                usableAreas.add(area);
            }
        });
        return usableAreas;
    }
}
