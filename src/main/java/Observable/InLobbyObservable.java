package Observable;

import Observer.InLobbyObserver;

/**
 * This interface is the InLobbyObservable which is implemented by the InLobbyModel class.
 * @author Lars Puente Blom
 * @author Beau Mosterd
 * @author Yoran de Vos
 * @version June 2019
 */

public interface InLobbyObservable {

    /**
     * Notifies an observer
     */
    void notifyAllObservers();

    /**
     * @param ob registers an observer to the observable
     */
    void register(InLobbyObserver ob);

    /**
     * @param ob unregisters an observer to the observable
     */
    void unregister(InLobbyObserver ob);


    /**
     * @return a String of player1
     */
    String getPlayer1();

    /**
     * @return a String of player2
     */
    String getPlayer2();

    /**
     * @return a String of player3
     */
    String getPlayer3();

    /**
     * @return a String of player4
     */
    String getPlayer4();


    /**
     * @return a Boolean that is False
     */

    boolean getStart();
}
