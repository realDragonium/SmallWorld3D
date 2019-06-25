package Observer;

import Observable.TimerObservable;

/** This interface is the TimerObserver which is implemented by the timerView class.
 * @author Mahad Musse
 * @version June 2019
 */

public interface TimerObserver {

    /**
     * @param ao has the TimerObservable parameter which is used to update the TimerView
     */
    void update(TimerObservable ao);
}
