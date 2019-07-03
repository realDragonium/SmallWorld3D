package Race;

import javafx.scene.paint.Color;

public class Amazones implements Race{

    @Override
    public String getName() {
        return "amazones";
    }

    @Override
    public int getFicheAmount() {
        return 6;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(9, 184, 73);
    }
}
