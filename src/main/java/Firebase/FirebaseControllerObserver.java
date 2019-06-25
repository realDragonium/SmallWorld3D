package Firebase;

import com.google.cloud.firestore.DocumentSnapshot;

/**
 * This interface is the FirebaseControllerObserver which is implemented by the classes: PlayerController, AreaController, ShopController , GameTurn
 * @author Beau Mosterd
 * @version June 2019
 */

public interface FirebaseControllerObserver {

    /**
     * @param ds is the parameter given to the update method.
     */
    void update(DocumentSnapshot ds);
}
