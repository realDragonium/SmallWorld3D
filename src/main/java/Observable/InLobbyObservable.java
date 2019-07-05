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
    String getPlayer(int i);

    boolean getPlayerState(int i);

    /**
     * @return a Boolean that is False
     */

    boolean getStart();

    boolean isHost();
}
