package Attacks;

import Controller.AreaController;
import Controller.CombinationController;
import Controller.GameController;
import Controller.PlayerController;
import Enums.AreaInfoEnum;

import java.util.*;

public class NormalAttack implements AttackType {

    @Override
    public void Attack(AreaController area, CombinationController combi) {
        area.attackArea(combi.getFiches(area.getDefenceValue()));
        combi.addArea(area);
        area.changeCombiOwner(combi);
        System.out.println("Normal attack CREATED!!!!!!!!");
    }

    @Override
    public AttackType nextAttack() {
        return new NormalAttack();
    }

    @Override
    public void checkAttackableAreas(CombinationController combi, Collection<AreaController> allAreas) {
        List<AreaController> areas = new ArrayList<>();
        Set<String> areaSet = new HashSet<>();

        //Voeg alle buren toe aan een Set
        combi.getAreas().forEach(area ->{
           areaSet.addAll(area.getNeighbours());
        });

        //laat alle buren de attackKnop zien
        allAreas.forEach(area -> {
            if(areaSet.contains(area.getId()) && area.isAttackAble()) {
                areas.add(area);
            }
        });

        //Filter je eigen areas eruit
        areas.removeAll(combi.getAreas());
        areas.forEach(area ->  area.setAreaInfoButton(AreaInfoEnum.ATTACK));

    }

}
