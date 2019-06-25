package Observable;

import Observer.AreaObserver;

/**
 * This interface is the AreaObservable which is implemented by the AreaModel class.
 * @author Beau Mosterd
 * @version June 2019
 */

public interface AreaObservable {

    /**
     * @param ao registers an observer to the observable
     */
    void register(AreaObserver ao);

    /**
     * Notifies an observer
     */
    void notifyObserver();


    /**
     * @return a boolean that says whether the area is active or not.
     */
    boolean getActive();


    /**
     * @return gets the amount of fiches that is on an area.
     */
    int getNumberOfFiches();


}
