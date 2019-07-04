package Race;

import Controller.CombinationController;
import Enums.AreaProperty;
import Model.CombinationModel;
import Points.SpecialPropertyPoints;
import javafx.scene.paint.Color;

public class Wizards implements Race {

    @Override
    public String getName() {
        return "wizards";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(89, 17, 83);
    }

    @Override
    public void activateRacePower(CombinationModel combi) {
        combi.racePoints = new SpecialPropertyPoints(AreaProperty.Magical);
    }
}