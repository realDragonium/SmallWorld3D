package Race;

import javafx.scene.paint.Color;

public class Wizards implements Race {

    @Override
    public String getName() {
        return "wizards";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(89, 17, 83);
    }
}