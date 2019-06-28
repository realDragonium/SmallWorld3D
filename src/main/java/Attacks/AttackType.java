package Attacks;

import Controller.AreaController;
import Controller.CombinationController;

public interface AttackType {

    void Attack(AreaController area, CombinationController combi);
}
