package Phase;

import java.util.List;
import Enum.GameViewEnum;

public interface Phase {
    Phase nextPhase();
    List<GameViewEnum> changeView();
    void resetTimer();

}
