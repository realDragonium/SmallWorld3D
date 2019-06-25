package Managers;

import Controller.AnimationController;
import Objects.Animatable;
import Objects.AnimationPoint;
import javafx.application.Platform;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AnimationsManager {
    private static AnimationsManager animManager;
    List<AnimationController> animations = new ArrayList<>();

    public static AnimationsManager getInstance(){
        if(animManager == null) animManager = new AnimationsManager();
        return animManager;
    }

    private AnimationsManager(){

        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> playAnimations());
            }
        };

        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(start, 0, 10);
    }

    private void playAnimations(){
        for(int x = 0; x < animations.size(); x++){
            animations.get(x).nextFrame();
        }
    }

    public void createMoveToAnimation(Animatable animObj, Translate newPosition){
        AnimationController newAnim = new AnimationController(animObj);
        double deltaX = newPosition.getX() - animObj.getCurrentPosition().getX();
        double deltaY = newPosition.getY() - animObj.getCurrentPosition().getY();
        for(int x = 0; x < 100; x++) {
            newAnim.addAnimationPoint(x, new AnimationPoint(new Translate(deltaX / 100, deltaY / 100)));
        }
    }
}
