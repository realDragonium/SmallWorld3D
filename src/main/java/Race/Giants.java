package Race;

import javafx.scene.paint.Color;

public class Giants implements Race {

    @Override
    public String getName() {
        return "ghouls";
    }

    @Override
    public int getFicheAmount() {
        return 7;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(116, 117, 81);
    }

}
