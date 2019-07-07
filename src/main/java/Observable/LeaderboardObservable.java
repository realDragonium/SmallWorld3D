package Observable;

import Controller.PlayerController;
import Observer.LeaderboardObserver;

import java.util.List;

public interface LeaderboardObservable {

    void register(LeaderboardObserver ob);

    void notifyAllObs();

    List<PlayerController> getPlayers();
}
