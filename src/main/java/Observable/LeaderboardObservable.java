package Observable;

import Observer.LeaderboardObserver;

import java.util.List;

public interface LeaderboardObservable {

    void register(LeaderboardObserver ob);

    void notifyAllObs();

    List<String> getPlayerNames();
    List<Integer> getPoints();
}
