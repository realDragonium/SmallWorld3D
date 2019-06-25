

package Observer;

import Observable.TurnObservable;

/** This interface is the TimerObserver which is implemented by the timerView class.
 * @author Martijn van der Steen
 * @version June 2019
 */

public interface TurnObserver {

    /**
     * @param to has the TurnObservable parameter which is used to update the TurnView
     */
    void update(TurnObservable to);
}
