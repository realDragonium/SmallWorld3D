package Controller;

import javafx.application.Platform;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    private GameController gameCon;
    private int timeLeft;
    int maxTime;
    private Timer gameTimer;
    private boolean current = false;
    private boolean timerAvailable = true;
//    private FirebaseServiceOwn fb;

    GameTimer(GameController gameCon, int time) {
        maxTime = time;
        this.gameCon = gameCon;
        timeLeft = time;
//        fb = SceneManager.getInstance().getApp().getFirebaseService();

        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> timerAction());
            }
        };

        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(start, 0, 1000);
    }

    private void timerAction(){
        timeLeft--;
        System.out.println(timeLeft);
        gameCon.getTimer().setTime(timeLeft);
        if (timeLeft == 0 && gameCon.getCurrentPlayer().getId().equals(gameCon.getMyPlayerId()) && timerAvailable) {
            Map<String, Object> info = new HashMap<>();
            current = !current;
            info.put("endPhase", current);
            info.put("time", maxTime);
            timerAvailable = false;
//            fb.resetTimer(info);
        }
    }

    void resetTimer(boolean current){
        this.current = current;
        timerAvailable = true;
        timeLeft = maxTime;
    }

    void endPhase(){
        Platform.runLater(()->timeLeft = 1);
    }

    public void stopTimer(){
        gameTimer.cancel();
    }
}
