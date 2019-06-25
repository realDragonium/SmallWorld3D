package Observable;

import Observer.RoundObserver;

/**
 * This interface is the RoundObservable which is implemented by the RoundModel class.
 * @author Martijn van der Steen
 * @version June 2019
 */

public interface RoundObservable {

    /**
     * @param ro registers an observer to the observable
     */
    void register(RoundObserver ro);

    /**
     * Notifies an observer
     */
    void notifyObservers();

    /**
     * @return an integer of the round that is currently active.
     */
    int getRound();
}
