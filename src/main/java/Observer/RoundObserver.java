package Observer;

import Observable.RoundObservable;

/** This interface is the RoundObserver which is implemented by the roundView class.
 * @author Martijn van der Steen
 * @version June 2019
 */
public interface RoundObserver {

    /**
     * @param ro has the RoundObservable parameter which is used to update the roundView
     */
    void update(RoundObservable ro);
}
