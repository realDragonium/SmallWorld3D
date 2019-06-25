package Observable;

import Observer.CameraObserver;
import javafx.scene.transform.Translate;

public interface CameraObservable {
    void register(CameraObserver ao);
    void notifyObserver();
    int getXAngle();
    int getYAngle();
    Translate getTranslate();
}
