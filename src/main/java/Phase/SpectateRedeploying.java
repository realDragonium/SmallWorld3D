package Phase;

import Controller.PhaseController;
import Enum.GameViewEnum;
import Enum.PhaseEnum;

import java.util.ArrayList;
import java.util.List;

public class SpectateRedeploying implements Phase {
    List<GameViewEnum> views = new ArrayList<>();

    public SpectateRedeploying(){

    }


    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.nextTurn();
    }

    @Override
    public String getName() {
        return "Redeploying";
    }

    @Override
    public List<GameViewEnum> getView() {
        return views;
    }
}
