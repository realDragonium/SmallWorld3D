package View;

import Controller.NotificationController;
import Observable.NotificationObservable;
import Observer.NotificationObserver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationView implements NotificationObserver {

    private Group group;
    private NotificationController notiCon;
    private List<Text> messages = new ArrayList<>();

    @FXML
    private Group root;

    public NotificationView(Group group, NotificationController notiCon){
        this.group = group;
        this.notiCon = notiCon;
    }

    public void initialize() {
        group.getChildren().add(root);
        notiCon.register(this);
    }

    private void showMessage(String message){
        Text text = new Text(message);
        messages.add(text);
        makeFancierMessage(text);
    }

    private void makeFancierMessage(Text text){
        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> text.setY(text.getLayoutY() + 10));
            }
        };

        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(start, 0, 500);

        TimerTask deleteMessage = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    messages.remove(text);
                    animTimer.cancel();
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(deleteMessage, 5000);
    }

    @Override
    public void update(NotificationObservable no) {
        showMessage(no.getActiveMessage().getMessage());
    }
}
