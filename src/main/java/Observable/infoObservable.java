package Observable;
import Observer.infoObserver;

/**
 * This interface is the infoObservable which is implemented by the infoModel class.
 * @author Mahad Musse
 * @version June 2019
 */

public interface infoObservable {

    /**
     * @param ob registers an observer to the observable
     */
    void register(infoObserver ob);


    /**
     * notifies an observer
     */
    void notifyAllObs();

    /**
     * @return a String that contains the info
     */
    String currentText();

}
