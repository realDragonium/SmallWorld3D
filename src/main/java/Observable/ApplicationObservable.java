package Observable;

import Enum.ApplicationViewEnum;
import Observer.ApplicationObserver;

public interface ApplicationObservable {
    void register(ApplicationObserver ao);
    void notifyObserver();
    ApplicationViewEnum getCurrentView();
}
