package Observable;

import Observer.NotificationObserver;
import Enum.NotificationEnum;

public interface NotificationObservable {
    void register(NotificationObserver no);
    void notifyObserver();
    NotificationEnum getActiveMessage();
}
