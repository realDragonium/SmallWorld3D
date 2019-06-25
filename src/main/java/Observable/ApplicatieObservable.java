package Observable;

import Enum.ApplicatieViewEnum;
import Observer.ApplicatieObserver;
import javafx.scene.SubScene;

public interface ApplicatieObservable {
    void register(ApplicatieObserver ao);
    void notifyObserver();
    ApplicatieViewEnum getCurrentView();
    SubScene get3dScene();
}
