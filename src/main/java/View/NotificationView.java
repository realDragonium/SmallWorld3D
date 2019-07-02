package View;

import Controller.NotificationController;
import Observable.NotificationObservable;
import Observer.NotificationObserver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import Enums.GameViewEnum;
public class NotificationView implements NotificationObserver {

    private Group group;
    private NotificationController notiCon;
    private List<Text> messages = new ArrayList<>();

    @FXML
    private Group root;

    public NotificationView(NotificationController notiCon){
        this.group = GameViewEnum.NOTIFICATION.getGroup();
        this.notiCon = notiCon;
    }

    public void initialize() {
        group.getChildren().add(root);
        notiCon.register(this);
    }

    private void showMessage(String message){
        Text text = new Text(message);
        root.getChildren().add(text);
        text.setFont(new Font(48));
        text.setFill(Color.RED);
        makeFancierMessage(text);
    }

    private void makeFancierMessage(Text text){
        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                text.setY(text.getY() - 10);
            }
        };

        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(start, 500, 50);

        TimerTask deleteMessage = new TimerTask() {
            @Override
            public void run() {
                animTimer.cancel();
                Platform.runLater(() -> root.getChildren().remove(text));
            }
        };
        Timer timer = new Timer();
        timer.schedule(deleteMessage, 2500);
    }

    @Override
    public void update(NotificationObservable no) {
        showMessage(no.getActiveMessage().getMessage());
    }
}
