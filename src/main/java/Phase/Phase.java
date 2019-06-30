package Phase;

import Controller.PhaseController;
import Enum.GameViewEnum;

import java.util.List;

public interface Phase {
    void nextPhase(PhaseController phaseCon);
    String getName();
    List<GameViewEnum> getView();
}
