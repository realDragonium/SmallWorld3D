package Power;

import Controller.CombinationController;

public interface Power {
    String getName();
    int getFicheAmount();
    void activatePower(CombinationController combiCon);
}
