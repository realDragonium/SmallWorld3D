package Attacks;

import Controller.AreaController;
import Controller.CombinationController;
import Controller.PlayerController;

public class NormalAttack implements AttackType {

    @Override
    public void Attack(AreaController area, CombinationController combi) {
        area.attackArea(combi.getFiches(area.getDefenceValue()));
        combi.addArea(area);
        area.changeCombiOwner(combi);
    }
}
