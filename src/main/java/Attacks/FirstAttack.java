package Attacks;

import Controller.AreaController;
import Controller.CombinationController;
import Controller.PlayerController;

public class FirstAttack implements  AttackType{


    @Override
    public void Attack(AreaController area, CombinationController combi) {
        if(!area.isBorderArea()) {
            System.out.println("Verkeerd land gekozen!");
            return;
        }
        final PlayerController player = combi.getPlayer();
        combi.addArea(area);
        area.attackArea(combi.getFiches(area.getDefenceValue()));
        area.setPlayerOwner(player);
    }
}
