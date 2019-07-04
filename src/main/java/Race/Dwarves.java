package Race;

import Enums.AreaProperty;
import Model.CombinationModel;
import Points.SpecialPropertyPoints;
import javafx.scene.paint.Color;

public class Dwarves implements Race {

    @Override
    public String getName() {
        return "dwarves";
    }

    @Override
    public int getFicheAmount() {
        return 3;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(191, 111, 36);
    }

    @Override
    public void activateRacePower(CombinationModel combi) {
        combi.racePoints = new SpecialPropertyPoints(AreaProperty.Mine);
    }
}
