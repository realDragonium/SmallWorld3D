package Controller;

import Model.NotificationModel;
import Objects.SpecialFXMLLoader;
import Observer.NotificationObserver;
import Enums.NotificationEnum;
import View.NotificationView;

import java.util.concurrent.Callable;

public class NotificationController {

    private NotificationModel model;
    private GameController gameCon;

    public NotificationController(GameController gameCon){
        model = new NotificationModel();
        this.gameCon = gameCon;
        createNotifiView();
    }

    private void createNotifiView() {
        new SpecialFXMLLoader().loader("/NotificationView.fxml", (Callable<NotificationView>) () -> new NotificationView(this));
    }

    public void register(NotificationObserver no){
        model.register(no);
    }

    public void setMessage(NotificationEnum message){
        model.newNotification(message);
    }
}
