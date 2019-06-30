package Observable;

import Observer.PhaseObserver;
import Phase.Phase;

public interface PhaseObservable {
    void register(PhaseObserver po);
    void notifyObserver();

    Phase getPhase();
}
