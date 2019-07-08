package Race;

import Model.CombinationModel;
import javafx.scene.paint.Color;

public class Losttribes implements Race {

    @Override
    public String getName() {
        return "losttribes";
    }

    @Override
    public int getFicheAmount() {
        return 13;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(133, 86, 84);
    }

    @Override
    public void activateRacePower(CombinationModel combi) {
        //DOESNT HAVE ONE
    }


}
