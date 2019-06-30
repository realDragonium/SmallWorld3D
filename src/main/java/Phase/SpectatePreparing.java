package Phase;

import Controller.PhaseController;
import Enum.PhaseEnum;
import Enum.GameViewEnum;

import java.util.ArrayList;
import java.util.List;

public class SpectatePreparing implements Phase {

    List<GameViewEnum> views = new ArrayList<>();

    public SpectatePreparing(){
    }

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.SPECTATERECONQUERING);
    }

    @Override
    public String getName() {
        return "Preparing";
    }

    @Override
    public List<GameViewEnum> getView() {
        return views;
    }

}
