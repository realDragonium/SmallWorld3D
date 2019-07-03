package Race;

import Controller.CombinationController;
import javafx.scene.paint.Color;

public class Halflings implements Race {

    @Override
    public String getName() {
        return "halflings";
    }

    @Override
    public int getFicheAmount() {
        return 6;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(129, 240, 142);
    }

    @Override
    public void activateRacePower(CombinationController combiCon) {

    }
}
