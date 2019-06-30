package Attacks;

import Controller.AreaController;
import Controller.CombinationController;
import Controller.PlayerController;

public class NormalAttack implements AttackType {

    @Override
    public void Attack(AreaController area, CombinationController combi) {
        final PlayerController player = combi.getPlayer();
        //combi.getRace().addArea(area);
        area.attackArea(combi.getFiches(area.getDefenceValue()));
        area.setPlayerOwner(player);
    }
}
