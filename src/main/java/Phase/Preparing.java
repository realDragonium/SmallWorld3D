package Phase;

import Controller.PhaseController;
import Enum.GameViewEnum;
import Enum.PhaseEnum;
import java.util.ArrayList;
import java.util.List;


public class Preparing implements Phase{
    List<GameViewEnum> views = new ArrayList<>();

    public Preparing(){

    }

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.CONQUERING);
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
