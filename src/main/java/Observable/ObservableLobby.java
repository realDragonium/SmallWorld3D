package Observable;

import Observer.LobbyObserver;

import java.util.List;

/**
 * This interface is the ObservableLobby which is implemented by the LobbyModel class.
 * @author Lars Puente Blom
 * @author Beau Mosterd
 * @author Yoran de Vos
 * @version June 2019
 */

public interface ObservableLobby {

    /**
     * @param ob registers an observer to the observable
     */
    void register(LobbyObserver ob);

    /**
     * @param mvo unregisters an observer to the observable
     */
    void unregister(LobbyObserver mvo);

    /**
     * Notifies an observer
     */
    void notifyAllObservers();

    /**
     * @return a List of Strings that contains all the lobbyNames
     */
    List<String> getLobbyName();
}
