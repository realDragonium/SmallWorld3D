package Race;

import Attacks.TritonsAttack;
import Controller.CombinationController;
import Model.CombinationModel;
import javafx.scene.paint.Color;

public class Tritons implements Race {

    @Override
    public String getName() {
        return "tritons";
    }

    @Override
    public int getFicheAmount() {
        return 6;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(82, 161, 204);
    }

    @Override
    public void activateRacePower(CombinationModel combi) {
        combi.raceAttackBoost = new TritonsAttack();
    }

}
