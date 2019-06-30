package Model;

import Observable.NotificationObservable;
import Observer.NotificationObserver;
import Enum.NotificationEnum;
import java.util.ArrayList;
import java.util.List;

public class NotificationModel implements NotificationObservable {
    private List<NotificationObserver> observers = new ArrayList<>();
    private NotificationEnum notification;


    public void newNotification(NotificationEnum noti){
        notification = noti;
        notifyObserver();
    }

    @Override
    public void register(NotificationObserver no) {
        observers.add(no);
    }

    @Override
    public void notifyObserver() {
        observers.forEach(obs -> obs.update(this));
    }

    @Override
    public NotificationEnum getActiveMessage() {
        return notification;
    }
}
