package Observable;

import Observer.LeaderboardObserver;

/**
 * This interface is the LeaderboardObservable which is implemented by the LeaderboardModel class.
 * @author Wino Sabelis
 * @version June 2019
 */
public interface LeaderboardObservable {

    /**
     * @param ob registers an observer to the observable
     */
    void register(LeaderboardObserver ob);

    /**
     * Notifies an observer
     */
    void notifyAllObs();

    /**
     * @return a String that contains an empty String
     */
    String getWaarde();

    /**
     * @return a String to gets the first place of the variable
     */
    String getPlace1();

    /**
     * @return a String to gets the second place of the variable
     */
    String getPlace2();

    /**
     * @return a String to gets the third place of the variable
     */
    String getPlace3();

    /**
     * @return a String to gets the first value.
     */
    String getValue1();

    /**
     * @return  a String to gets the second value.
     */
    String getValue2();

    /**
     * @return a String to gets the third value.
     */
    String getValue3();

}
