package Phase;

import Enum.GameViewEnum;

import java.util.ArrayList;
import java.util.List;

public class PhaseNone implements Phase {
    List<GameViewEnum> views = new ArrayList<>();

    public PhaseNone(){
        views.add(GameViewEnum.PLAYER);
    }


    @Override
    public Phase nextPhase() {
        return new Preparing();
    }

    @Override
    public List<GameViewEnum> changeView() {
        return views;
    }

    @Override
    public void resetTimer() {

    }
}
