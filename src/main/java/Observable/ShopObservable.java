package Observable;

import Observer.ShopObserver;

/**
 * This interface is the ShopObservable which is implemented by the RoundModel class.
 * @author Yoran de Vos
 * @version June 2019
 */

public interface ShopObservable {

    /**
     * @param so registers an observer to the observable
     */
    void register(ShopObserver so);

    /**
     * Notifies an observer
     */
    void notifyObservers();

    /**
     * @return an integer of the round.
     */
    int getRound();

    /**
     * @param item is given to the method getRace to add the racecombo
     * @return a String of the player
     */
    String getRace(int item);

    /**
     * @param item is given to the method getPowerOld to add the racecombo
     * @return a String of the power
     */
    String getPower(int item);
}
