package Controller;

import Model.TimerModel;
import Observer.TimerObserver;

public class TimerController {

    private PhaseController phaseController;
    private TimerModel model = new TimerModel();
    private GameController gameCon;

    public TimerController(GameController gameController) {
        gameCon = gameController;
    }

    public void registerObs(TimerObserver timerObs){
        model.register(timerObs);
    }

    TimerController(PhaseController phaseController){
        this.phaseController = phaseController;
    }

    public long getElapsedTime(){
        return model.getSeconds();
    }


    void setTime(int timer) {
        model.setTimer(timer);
    }
}
