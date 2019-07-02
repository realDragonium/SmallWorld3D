package Race;

import javafx.scene.paint.Color;

public class Halflings implements Race {

    @Override
    public String getName() {
        return "halflings";
    }

    @Override
    public int getFicheAmount() {
        return 9;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(129, 240, 142);
    }
}
