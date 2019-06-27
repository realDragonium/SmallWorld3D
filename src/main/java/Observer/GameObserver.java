package Observer;

import Observable.GameObservable;

public interface GameObserver {
    void update(GameObservable go);
}
