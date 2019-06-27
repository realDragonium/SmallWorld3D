package Phase;
import Enum.GameViewEnum;
import java.util.ArrayList;
import java.util.List;

public class Redeploying implements Phase {
    List<GameViewEnum> views = new ArrayList<>();

    public Redeploying(){
        views.add(GameViewEnum.REDEPLOY);
    }

    @Override
    public Phase nextPhase() {
        return new PhaseNone();
    }

    @Override
    public List<GameViewEnum> changeView() {
        return views;
    }

    @Override
    public void resetTimer() {

    }
}
