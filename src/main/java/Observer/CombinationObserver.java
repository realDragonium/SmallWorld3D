package Observer;

import Observable.CombinationObservable;


/** This interface is the CombinationObserver which is implemented by the CombinationView class.
 *
 * @author Beau Mosterd
 * @version June 2019
 */
public interface CombinationObserver {

    /**
     * @param co has the CombinationObserverable parameter which is used to update the CombinationView
     */
    void update(CombinationObservable co);
}
