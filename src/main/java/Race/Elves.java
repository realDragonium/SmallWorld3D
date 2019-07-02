package Race;

import javafx.scene.paint.Color;

public class Elves implements Race {

    @Override
    public String getName() {
        return "elves";
    }

    @Override
    public int getFicheAmount() {
        return 8;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(209, 121, 194);
    }


}
