package Phase;

import Enum.GameViewEnum;

import java.util.ArrayList;
import java.util.List;

public class Conquering implements Phase{
    List<GameViewEnum> views = new ArrayList<>();

    public Conquering(){
        views.add(GameViewEnum.ATTACK);
    }

    @Override
    public Phase nextPhase() {
        return new Redeploying();
    }

    @Override
    public List<GameViewEnum> changeView() {
        return views;
    }

    @Override
    public void resetTimer() {

    }
}
