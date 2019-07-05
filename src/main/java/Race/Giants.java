package Race;

import Attacks.GiantAttack;
import Controller.CombinationController;
import Model.CombinationModel;
import javafx.scene.paint.Color;

public class Giants implements Race {

    @Override
    public String getName() {
        return "giants";
    }

    @Override
    public int getFicheAmount() {
        return 6;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(116, 117, 81);
    }

    @Override
    public void activateRacePower(CombinationModel combi) {
        combi.raceAttackBoost = new GiantAttack();
    }

}
