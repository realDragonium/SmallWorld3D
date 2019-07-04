package Attacks;

import Controller.AreaController;
import Controller.CombinationController;
import Enums.AreaType;

import java.util.*;

public class NormalAttack implements AttackableAreas {

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
        List<AreaController> areas = new ArrayList<>();
        Set<String> areaSet = new HashSet<>();
        List<AreaType> areaTypes = combi.getAttackableTypes();

        //Voeg alle buren toe aan een Set
        combi.getAreas().forEach(area -> areaSet.addAll(area.getNeighbours()));

        //laat alle buren de attackKnop zien
        allAreas.forEach(area -> {
            if(areaSet.contains(area.getId()) && area.isAttackAble()
                    && areaTypes.contains(area.getAreaType())) {
                areas.add(area);
            }
        });

        //Filter je eigen areas eruit
        areas.removeAll(combi.getAreas());

        return areas;
    }

}
