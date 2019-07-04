package Race;

import Controller.FicheController;
import Model.CombinationModel;
import Power.EveryRoundPower;
import javafx.scene.paint.Color;

public class Skeletons implements Race, EveryRoundPower {

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
    public void activateRacePower(CombinationModel combi) {
        combi.everyRoundPower = this;
    }

    @Override
    public void doAction(CombinationModel combi) {
        int numberOfFiches =  combi.thisRoundConquered.size()/2;
        for (int i = 0; i < numberOfFiches ; i++) {
            FicheController fiche = new FicheController(1, combi.race.getName());
            combi.addFiche(fiche);
        }
    }
}
