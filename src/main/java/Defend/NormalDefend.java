package Defend;

import Controller.CombinationController;

public class NormalDefend implements Defend {

    @Override
    public void retreat(CombinationController combi) {
        combi.fichePoof();
    }

}
