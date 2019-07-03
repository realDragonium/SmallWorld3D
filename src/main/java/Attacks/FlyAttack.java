package Attacks;

import Controller.AreaController;
import Controller.CombinationController;

import java.util.Collection;
import java.util.List;

public class FlyAttack implements AttackType {
    @Override
    public void Attack(AreaController area, CombinationController combi) {
        
    }

    @Override
    public AttackType nextAttack() {
        return null;
    }

    @Override
    public List<AreaController> checkAttackableAreas(CombinationController combi, Collection<AreaController> allAreas) {
        return null;
    }
}
