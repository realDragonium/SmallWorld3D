package Attacks;

import Controller.AreaController;
import Controller.CombinationController;
import Controller.FicheController;
import Controller.PlayerController;

import java.util.Stack;

public class FirstAttack implements  AttackType{

    @Override
    public void Attack(AreaController area, CombinationController combi) {
        if(area.isBorderArea()) {
            combi.addArea(area);
            area.attackArea(combi.getFiches(area.getDefenceValue()));
            area.changeCombiOwner(combi);
            combi.setAttackType(new NormalAttack());
        }
        else {
            System.out.println("Verkeerd land gekozen!");
        }
    }
}
