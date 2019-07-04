package Race;

import Controller.CombinationController;
import javafx.scene.paint.Color;

public interface Race {
    String getName();
    int getFicheAmount();
    Color getRaceColor();
    void activateRacePower(CombinationController combiCon);
}
