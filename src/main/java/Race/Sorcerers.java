package Race;

import javafx.scene.paint.Color;

public class Sorcerers implements Race {

    @Override
    public String getName() {
        return "sorcerers";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(173, 33, 23);
    }
}
