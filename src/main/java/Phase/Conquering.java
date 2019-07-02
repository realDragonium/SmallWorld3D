package Phase;

import Controller.PhaseController;
import Enum.GameViewEnum;
import Enum.PhaseEnum;
import java.util.ArrayList;
import java.util.List;

public class Conquering implements Phase{
    List<GameViewEnum> views = new ArrayList<>();

    public Conquering(){

    }

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.REDEPLOYING);
    }


    @Override
    public String getName() {
        return "Conquering";
    }

    @Override
    public List<GameViewEnum> getView() {
        return views;
    }
}
