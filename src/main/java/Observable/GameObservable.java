package Observable;

import Observer.GameObserver;
import Enums.GameViewEnum;

import java.util.List;

public interface GameObservable {
    void register(GameObserver go);
    void notifyObserver();
    List<GameViewEnum> getCurrenViews();
}
