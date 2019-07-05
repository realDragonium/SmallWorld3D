package Observable;

import Observer.TurnObserver;

public interface TurnObservable {
    void register(TurnObserver to);
    void notifyObservers();
    String getPlayerName();
    String getRaceName();

}
