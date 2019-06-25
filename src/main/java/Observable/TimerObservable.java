package Observable;

import Observer.TimerObserver;

/**
 * This interface is the TimerObservable which is implemented by the TimerModel class.
 * @author Mahad Musse
 * @version June 2019
 */
public interface TimerObservable {

    /**
     * @param to registers an observer to the observable
     */
    void register(TimerObserver to);

    /**
     * @param to unregisters an observer to the observable
     */
    void unregister(TimerObserver to);

    /**
     * @return an integer of the seconds
     */
    int getSeconds();

    /**
     * Notifies an observer
     */
    void notifyAllObservers();
}
