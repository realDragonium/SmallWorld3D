package Power;

import Model.CombinationModel;

public interface Power {
    String getName();
    int getFicheAmount();
    void activatePower(CombinationModel combi);
}
