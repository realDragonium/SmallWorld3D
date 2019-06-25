package Controller;

import Managers.SceneManager;
import Model.TimerModel;
import Observer.TimerObserver;

public class TimerController {

    private GameTurn gameTurn;
    private TimerModel model = new TimerModel();

    public void registerObs(TimerObserver timerObs){
        model.register(timerObs);
    }

    TimerController(GameTurn gameTurn){
        this.gameTurn = gameTurn;
//        SceneManager.getInstance().loadTimer(this);
    }

    public long getElapsedTime(){
        return model.getSeconds();
    }


    void setTime(int timer) {
        model.setTimer(timer);
    }
}
