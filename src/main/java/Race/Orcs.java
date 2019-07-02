package Race;


import javafx.scene.paint.Color;

public class Orcs implements Race {

    @Override
    public String getName() {
        return "orcs";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(34, 79, 27);
    }


}
