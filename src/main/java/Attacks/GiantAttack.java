package Attacks;

import Controller.AreaController;

public class GiantAttack implements AttackBoost {

    @Override
    public int getBoost(AreaController area) {
        for (String neigbour : area.getNeighbours()) {
            if (neigbour.contains("water")) return -1;
        }
        return 0;
    }
}