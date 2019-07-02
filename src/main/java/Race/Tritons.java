package Race;

import javafx.scene.paint.Color;

public class Tritons implements Race {

    @Override
    public String getName() {
        return "tritons";
    }

    @Override
    public int getFicheAmount() {
        return 8;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(82, 161, 204);
    }
}
