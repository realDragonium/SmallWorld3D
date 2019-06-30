package Controller;

import Model.NotificationModel;
import Observer.NotificationObserver;

public class NotificationController {

    private NotificationModel model;
    private GameController gameCon;


    public void NotificationController(GameController gameCon){
        model = new NotificationModel();
        this.gameCon = gameCon;
    }

    public void register(NotificationObserver no){
        model.register(no);
    }
}
