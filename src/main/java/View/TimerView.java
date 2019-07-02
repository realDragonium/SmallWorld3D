package View;

import Controller.TimerController;
import Observable.TimerObservable;
import Observer.TimerObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Enums.GameViewEnum;
public class TimerView implements TimerObserver {

    private TimerController timerCon;
    private Group group;

    @FXML
    public Pane pane;

    @FXML
    public Text timer;

    public TimerView(TimerController timerCon){
        this.group = GameViewEnum.TIMER.getGroup();
        this.timerCon = timerCon;
    }

    public void initialize(){
        group.getChildren().add(pane);
        timerCon.registerObs(this);
    }

    @Override
    public void update(TimerObservable ao) {
        String minutes = Integer.toString((ao.getSeconds() - ao.getSeconds() % 60) / 60);
        String seconds = Integer.toString(ao.getSeconds() % 60);
        if(seconds.length() == 1){
            seconds = "0" + seconds;
        }
        String time = "Time left: " + minutes + ":" + seconds ;
        timer.setText(time);
    }


}
