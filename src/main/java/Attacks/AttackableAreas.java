package Attacks;

import Controller.AreaController;
import Controller.CombinationController;

import java.util.Collection;
import java.util.List;

public interface AttackableAreas {

    void Attack(AreaController area, CombinationController combi);
    AttackableAreas nextAttack();
    List<AreaController> checkAttackableAreas(CombinationController combi, Collection<AreaController> allAreas);
}
