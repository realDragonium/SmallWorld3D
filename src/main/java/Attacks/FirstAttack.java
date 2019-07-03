package Attacks;

import Controller.*;
import Enums.AreaType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FirstAttack implements AttackableAreas {

    @Override
    public void Attack(AreaController area, CombinationController combi) {
        combi.addArea(area);
        area.attackArea(combi.getFiches(area.getDefenceValue()));
        area.changeCombiOwner(combi);
    }

    @Override
    public AttackableAreas nextAttack() {
        return new NormalAttack();
    }

    @Override
    public List<AreaController> checkAttackableAreas(CombinationController combi, Collection<AreaController> allAreas) {
        List<AreaController> usableAreas = new ArrayList<>();
        List<AreaType> areaTypes = combi.getAttackableTypes();
        allAreas.forEach(area -> {
            if (area.isBorderArea() && area.isAttackAble()
                    && areaTypes.contains(area.getAreaType())) {
                usableAreas.add(area);
            }
        });
        return usableAreas;
    }

}
