package Race;

import Model.CombinationModel;
import javafx.scene.paint.Color;

public interface Race {
    String getName();
    int getFicheAmount();
    Color getRaceColor();
    void activateRacePower(CombinationModel combi);
}
