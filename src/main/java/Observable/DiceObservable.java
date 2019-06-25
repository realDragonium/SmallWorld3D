package Observable;

import Observer.DiceObserver;

/**
 * This interface is the DiceObservable which is implemented by the DiceModel class.
 * @author Wino Sabelis
 * @version June 2019
 */

public interface DiceObservable {

    /**
     * @param ob registers an observer to the observable
     */
    void register(DiceObserver ob);

    /**
     * Notifies an observer
     */
    void notifyAllObs();


    /**
     * @return an integer that contains the value of the dice
     */
    int getWaarde();

    /**
     * @return a boolean that shows if the dice image is playing
     */
    boolean isPlaying();


}
