package Race;

import javafx.scene.paint.Color;

public class Losttribes implements Race {

    @Override
    public String getName() {
        return "losttribes";
    }

    @Override
    public int getFicheAmount() {
        return 1;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(133, 86, 84);
    }


}
