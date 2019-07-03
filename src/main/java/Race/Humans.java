package Race;

import Controller.CombinationController;
import javafx.scene.paint.Color;

public class Humans implements Race {

    @Override
    public String getName() {
        return "humans";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(222, 215, 18);
    }

    @Override
    public void activateRacePower(CombinationController combiCon) {

    }
}
