package Attacks;

import Controller.AreaController;

public class CommandoAttackBoost implements  AttackBoost {

    @Override
    public int getBoost(AreaController area) {
        return -1;
    }
}
