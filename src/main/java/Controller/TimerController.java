package Controller;

import Model.TimerModel;
import Objects.SpecialFXMLLoader;
import Observer.TimerObserver;
import View.TimerView;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class TimerController {

    private PhaseController phaseController;
    private TimerModel model = new TimerModel();
    private GameController gameCon;
    private boolean running = false;

    public TimerController(GameController gameController) {

        createTimerView();
        gameCon = gameController;
        startTimer();
    }

    public void startTimer(){

        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() ->{
                        if(running)
                        timerAction();
                });
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(start, 0, 1000);
    }

    private void timerAction(){
        model.setTimer(model.getSeconds() - 1);

        if(model.getSeconds() == 0&& model.isMyTurn()){
            gameCon.getPhaseCon().nextPhase();
        }

        if(model.getSeconds() == -10){
            gameCon.createVote(3, "player lost connection, skip his turn?");
        }
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

    public void restartTimer(int time, boolean myTurn) {
        model.setMyTurn(myTurn);
        model.setTimer(time);
        running = true;
    }
}
