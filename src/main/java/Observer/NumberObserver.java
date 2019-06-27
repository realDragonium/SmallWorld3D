package Observer;

import Observable.NumberObservable;

public interface NumberObserver {
    void update(NumberObservable no);
}
