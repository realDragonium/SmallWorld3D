package Phase;

import Controller.PhaseController;
import Enum.GameViewEnum;
import Enum.PhaseEnum;
import java.util.ArrayList;
import java.util.List;

public class PhaseNone implements Phase {
    List<GameViewEnum> views = new ArrayList<>();

    public PhaseNone(){
        views.add(GameViewEnum.PLAYER);
    }


    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.PREPARING);
        phaseCon.changeView();
    }

    @Override
    public String getName() {
        return "None";
    }

    @Override
    public List<GameViewEnum> getView() {
        return views;
    }
}
