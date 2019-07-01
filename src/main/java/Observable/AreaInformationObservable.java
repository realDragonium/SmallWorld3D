package Observable;

import Controller.AreaController;
import Observer.AreaInformationObserver;

public interface AreaInformationObservable {
    void register(AreaInformationObserver ob);
    void notifyAllObs();
    AreaController getArea();
}
