package Observable;

import Observer.NotificationObserver;
import Enums.NotificationEnum;

public interface NotificationObservable {
    void register(NotificationObserver no);
    void notifyObserver();
    NotificationEnum getActiveMessage();
}
