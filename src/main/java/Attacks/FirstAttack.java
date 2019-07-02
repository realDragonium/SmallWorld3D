package Attacks;

import Controller.*;
import Enums.AreaInfoEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class FirstAttack implements  AttackType{

    @Override
    public void Attack(AreaController area, CombinationController combi) {
        if(area.isBorderArea()) {
            combi.addArea(area);
            area.attackArea(combi.getFiches(area.getDefenceValue()));
            area.changeCombiOwner(combi);
        }
        else {
            System.out.println("Verkeerd land gekozen!");
        }
    }

    @Override
    public AttackType nextAttack() {
        return new NormalAttack();
    }

    @Override
    public void checkAttackableAreas(CombinationController combi, Collection<AreaController> allAreas) {
        allAreas.forEach(area -> {
            if(area.isBorderArea() && area.isAttackAble()){
                area.setAreaInfoButton(AreaInfoEnum.ATTACK);
            }

        });
    }

}
