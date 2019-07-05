package Observable;

import Controller.CombinationController;
import Observer.PlayerObserver;

import java.util.List;


public interface PlayerObservable {

    void register(PlayerObserver po);

    void notifyObserver();

    int getPoints();

    boolean hasActiveCombination();

    CombinationController getCurrentCombi();

    List<CombinationController> getDeclineCombies();
}
