package Firebase;

import java.util.List;

/**
 * This interface is the FirebaseLobbyObserver.
 * @author Beau Mosterd
 * @version June 2019
 */
public interface FirebaseLobbyObserver {

    /**
     * @param lijst is the parameter given to the update method.
     */
    void update(List<String> lijst);
}
