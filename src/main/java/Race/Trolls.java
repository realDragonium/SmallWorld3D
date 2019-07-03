package Race;

import Controller.CombinationController;
import javafx.scene.paint.Color;

public class Trolls implements Race {

    @Override
    public String getName() {
        return "trolls";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(97, 93, 93);
    }

    @Override
    public void activateRacePower(CombinationController combiCon) {

    }
}
