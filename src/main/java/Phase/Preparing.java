package Phase;

import Enum.GameViewEnum;

import java.util.ArrayList;
import java.util.List;

public class Preparing implements Phase{
    List<GameViewEnum> views = new ArrayList<>();

    public Preparing(){
        views.add(GameViewEnum.SHOP);
    }

    @Override
    public Phase nextPhase() {
        return new Conquering();
    }

    @Override
    public List<GameViewEnum> changeView() {
        return views;
    }

    @Override
    public void resetTimer() {

    }
}
