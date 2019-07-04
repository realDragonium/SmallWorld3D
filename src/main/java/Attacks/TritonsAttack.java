package Attacks;

import Controller.AreaController;

public class TritonsAttack implements AttackBoost {

    @Override
    public int getBoost(AreaController area) {
        if(area.isNextToWater()) return -1;
        return 0;
    }
}
