package Observer;

import Observable.AreaObservable;

/** This interface is the AreaObserver which is implemented by the Area2DView class.
 *
 * @author Beau Mosterd
 * @version June 2019
 */
public interface AreaObserver {

    /**
     * @param ao has the AreaObservable parameter which is used to update the Area2DView
     */
    void update(AreaObservable ao);
}
