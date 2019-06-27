package Observable;

import Observer.NumberObserver;
import javafx.scene.transform.Translate;

public interface NumberObservable {
    void register(NumberObserver ob);
    void notifyAllObs();
    Translate getPosition();
    double getYAngle();

    int getCurNumber();
}
