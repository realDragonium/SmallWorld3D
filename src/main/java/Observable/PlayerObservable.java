package Observable;

import Observer.PlayerObserver;

/**
 * This interface is the PlayerObservable which is implemented by the PlayerModel class.
 * @author Yoran de Vos
 * @version June 2019
 */

public interface PlayerObservable {

    /**
     * @param po registers an observer to the observable
     */
    void register(PlayerObserver po);

    /**
     * Notifies an observer
     */
    void notifyObserver();

    /**
     * @return the amount of fiches a player has.
     */
    int getFiches();

    /**
     * @return the amount of points a player has.
     */
    int getPunten();
}
