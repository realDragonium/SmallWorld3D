package Attacks;

import Controller.AreaController;

public class NoAttackBoost implements AttackBoost {

    @Override
    public int getBoost(AreaController area) {
        return 0;
    }
}
