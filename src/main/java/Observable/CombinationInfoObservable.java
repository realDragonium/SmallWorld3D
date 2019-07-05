package Observable;

import Observer.CombinationInfoObserver;

public interface CombinationInfoObservable {
    /**
     * @param ob registers an observer to the observable
     */
    void register(CombinationInfoObserver ob);

    /**
     * Notifies an observer
     */
    void notifyAllObs();

    String getRace();

    String getPower();

    boolean inShop();
}
