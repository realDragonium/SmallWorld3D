package Observable;

import Observer.LobbySettingsObserver;

/**
 * This interface is the LobbySettingsObservable which is implemented by the LobbySettingsModel class.
 * @author Lars Puente Blom
 * @author Beau Mosterd
 * @author Yoran de Vos
 * @version June 2019
 */

public interface LobbySettingsObservable {

    /**
     * @param ob registers an observer to the observable
     */
    void register(LobbySettingsObserver ob);

    /**
     * @param mvo unregisters an observer to the observable
     */
    void unregister(LobbySettingsObserver mvo);

    /**
     * Notifies an observer
     */
    void notifyAllObservers();
}
