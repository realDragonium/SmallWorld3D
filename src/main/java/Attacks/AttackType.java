package Attacks;

import Controller.AreaController;
import Controller.CombinationController;
import Controller.GameController;

import java.util.Collection;
import java.util.List;

public interface AttackType {

    void Attack(AreaController area, CombinationController combi);
    AttackType nextAttack();
    void checkAttackableAreas(CombinationController combi, Collection<AreaController> allAreas);
}
