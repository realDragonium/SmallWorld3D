package Observable;

import Observer.GameObserver;
import Enum.GameViewEnum;

import java.util.List;

public interface GameObservable {
    void register(GameObserver go);
    void notifyObserver();
    List<GameViewEnum> getCurrenViews();
}
