package Controller;

import Model.NotificationModel;
import Observer.NotificationObserver;
import Enum.NotificationEnum;

public class NotificationController {

    private NotificationModel model;
    private GameController gameCon;

    public NotificationController(GameController gameCon){
        model = new NotificationModel();
        this.gameCon = gameCon;
    }

    public void register(NotificationObserver no){
        model.register(no);
    }

    public void setMessage(NotificationEnum message){
        model.newNotification(message);
    }
}
