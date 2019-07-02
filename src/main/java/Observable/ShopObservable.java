package Observable;

import Controller.CombinationController;
import Observer.ShopObserver;
import javafx.scene.transform.Translate;

import java.util.List;

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

    List<CombinationController> getShopItems();

    Translate getItemPosition(int i);

    Translate getShopPosition();
}
