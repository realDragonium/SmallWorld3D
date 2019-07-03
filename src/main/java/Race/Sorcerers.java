package Race;

import Controller.CombinationController;
import javafx.scene.paint.Color;

public class Sorcerers implements Race {

    @Override
    public String getName() {
        return "sorcerers";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(173, 33, 23);
    }

    @Override
    public void activateRacePower(CombinationController combiCon) {

    }
}
