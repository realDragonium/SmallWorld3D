package Race;

import Controller.CombinationController;
import Enums.NotificationEnum;
import javafx.scene.paint.Color;

public class Ratmen implements Race {

    @Override
    public String getName() {
        return "ratmen";
    }

    @Override
    public int getFicheAmount() {
        return 8;
    }

    @Override
    public Color getRaceColor() {
        return Color.rgb(69, 63, 63);
    }

    @Override
    public void activateRacePower(CombinationController combiCon) {
//        combiCon.getPlayer().getGameCon().setMessage(NotificationEnum.RATMEN);
    }
}
