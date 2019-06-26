package Observable;

import Observer.FicheObserver;
import javafx.scene.transform.Translate;

public interface FicheObservable {
    void register(FicheObserver ob);
    void notifyAllObs();
    Translate getPosition();
}
