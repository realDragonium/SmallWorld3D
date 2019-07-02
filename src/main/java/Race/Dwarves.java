package Race;

import javafx.scene.paint.Color;

public class Dwarves implements Race {

    @Override
    public String getName() {
        return "dwarves";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(191, 111, 36);
    }
}
