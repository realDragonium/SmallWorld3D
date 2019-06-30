package Phase;

import Controller.PhaseController;
import Enum.GameViewEnum;
import Enum.PhaseEnum;

import java.util.ArrayList;
import java.util.List;

public class SpectateConquering implements Phase {

    List<GameViewEnum> views = new ArrayList<>();

    public SpectateConquering(){

    }

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.SPECTATEREDEPLOYING);
    }

    @Override
    public String getName() {
        return "Conquering";
    }

    @Override
    public List<GameViewEnum> getView() {
        return null;
    }
}
