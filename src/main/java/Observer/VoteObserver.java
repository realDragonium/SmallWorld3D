package Observer;

import Observable.VoteObservable;

public interface VoteObserver {
    void update(VoteObservable vo);
}
