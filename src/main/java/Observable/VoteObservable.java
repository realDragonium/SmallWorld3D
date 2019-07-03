package Observable;

import Observer.DiceObserver;
import Observer.VoteObserver;

public interface VoteObservable {

    /**
     * @param ob registers an observer to the observable
     */
    void register(VoteObserver ob);

    /**
     * Notifies an observer
     */
    void notifyAllObs();

    int getCurrentVotes();

    int getVotesNeeded();


    boolean hasVoted();

    String getMessage();
}
