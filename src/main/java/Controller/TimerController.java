package Controller;

import Model.TimerModel;
import Objects.SpecialFXMLLoader;
import Observer.TimerObserver;
import View.TimerView;

import java.util.concurrent.Callable;

public class TimerController {

    private PhaseController phaseController;
    private TimerModel model = new TimerModel();
    private GameController gameCon;

    public TimerController(GameController gameController) {
        gameCon = gameController;
        createTimerView();
    }

    private void createTimerView() {
        new SpecialFXMLLoader().loader("/TimerView.fxml", (Callable<TimerView>) () -> new TimerView(this));
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
