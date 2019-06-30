package Phase;
import Controller.PhaseController;
import Enum.GameViewEnum;
import java.util.ArrayList;
import java.util.List;
import Enum.PhaseEnum;


public class Redeploying implements Phase {
    List<GameViewEnum> views = new ArrayList<>();

    public Redeploying(){
        views.add(GameViewEnum.REDEPLOY);
    }

    @Override
    public void nextPhase(PhaseController phaseCon) {
        phaseCon.setPhase(PhaseEnum.NONE);
        phaseCon.changeView();
        phaseCon.nextTurn();
    }

    @Override
    public String getName() {
        return "Redeploying";
    }

    @Override
    public List<GameViewEnum> getView() {
        return null;
    }
}
