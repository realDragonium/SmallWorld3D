package Race;

import Controller.CombinationController;
import Model.CombinationModel;
import javafx.scene.paint.Color;

public class Ghouls implements Race {

    @Override
    public String getName() {
        return "ghouls";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(155, 174, 222);
    }

    @Override
    public void activateRacePower(CombinationModel combi) {

    }

}
