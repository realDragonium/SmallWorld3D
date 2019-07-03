package Race;

import Controller.CombinationController;
import javafx.scene.paint.Color;

public class Skeletons implements Race {

    @Override
    public String getName() {
        return "skeletons";
    }

    @Override
    public int getFicheAmount() {
        return 6;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(242, 231, 230);
    }

    @Override
    public void activateRacePower(CombinationController combiCon) {

    }
}
