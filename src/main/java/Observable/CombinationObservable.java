package Observable;

import Observer.CombinationObserver;

/**
 * This interface is the CombinationObservable which is implemented by the CombinationModel class.
 * @author Beau Mosterd
 * @version June 2019
 */

public interface CombinationObservable {

    /**
     * @param mvo registers an observer to the observable
     */
    void register(CombinationObserver mvo);


    /**
     * @param mvo unregisters an observer to the observable
     */
    void unregister(CombinationObserver mvo);


    /**
     *  Notifies an observer
     */
    void notifyAllObservers();

    /**
     * @return a String which contains the raceId.
     */
    String getRaceId();

    /**
     * @return a String which contains the powerId.
     */
    String getPowerId();


}
