package Observer;

import Observable.InLobbyObservable;

/** This interface is the InlobbyObserver which is implemented by the inlobbyView class.
 * @author Lars Puente Blom
 * @author Beau Mosterd
 * @author Yoran de Vos
 * @version June 2019
 */

public interface InLobbyObserver {

    /**
     * @param ilo as the InLobbyObservable parameter which is used to update the inlobbyView
     */
    void update(InLobbyObservable ilo);
}
