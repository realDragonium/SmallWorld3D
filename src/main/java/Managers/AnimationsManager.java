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
    int animationFPS = 20;
    List<AnimationController> translateAnimations = new ArrayList<>();
    List<AnimationController> rotateAnimations = new ArrayList<>();

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
        animTimer.scheduleAtFixedRate(start, 0, 1000/animationFPS);
    }

    private void addToAnimationsRot(AnimationController newAnim){

        for(int i =0; i < rotateAnimations.size(); i++){
            if( rotateAnimations.get(i).getAnimObject().equals(newAnim.getAnimObject())){
                rotateAnimations.remove(i);
            }
        }
        rotateAnimations.add(newAnim);
    }

    private void addToAnimationsTrans(AnimationController newAnim){

        for(int i =0; i < translateAnimations.size(); i++){
            if( translateAnimations.get(i).getAnimObject().equals(newAnim.getAnimObject())){
                translateAnimations.remove(i);
            }
        }
        translateAnimations.add(newAnim);
    }

    private void playAnimations(){
        for(int x = 0; x <  rotateAnimations.size(); x++){
            rotateAnimations.get(x).nextFrame();
        }
        for(int x = 0; x <  translateAnimations.size(); x++){
            translateAnimations.get(x).nextFrame();
        }
    }

    public void createUpAndDownAnimation(Animatable animObj, double height, double seconds){
        AnimationController newAnim = new AnimationController(animObj, true);
        int frames = (int)seconds * animationFPS;
        double yDelta = height / frames;
        for(int x = 1; x <= frames / 2; x++) {
            Translate point = new Translate(0, yDelta, 0);
            newAnim.addAnimationPoint(x, new AnimationPoint(point));
        }
        for(int x = frames / 2 + 1; x <= frames; x++) {
            Translate point = new Translate(0, -yDelta, 0);
            newAnim.addAnimationPoint(x, new AnimationPoint(point));
        }
        addToAnimationsTrans(newAnim);
    }

    public void createMoveToAnimationArch(Animatable animObj, Translate newPosition, int seconds){
        AnimationController newAnim = new AnimationController(animObj, false);

        int frames = seconds * animationFPS;

        double deltaX = newPosition.getX() - animObj.getCurrentPosition().getX();
        double deltaY = newPosition.getY() - animObj.getCurrentPosition().getY();
        double deltaZ = newPosition.getZ() - animObj.getCurrentPosition().getZ();


        double lastHeight = 0.4 * ((0 - frames/2) * (0 - frames/2)) - 100;
        for(int x = 1; x <= frames; x++) {
            double height = 0.4 * ((x - frames/2) * (x -frames/2)) - 100;
            double deltaHeight = height - lastHeight;
            lastHeight = height;
            Translate point = new Translate(deltaX / frames, deltaY / frames + deltaHeight, deltaZ / frames);
            newAnim.addAnimationPoint(x, new AnimationPoint(point));
        }
        addToAnimationsTrans(newAnim);
    }

    public void createMoveToAnimation(Animatable animObj, Translate newPosition, int seconds){
        AnimationController newAnim = new AnimationController(animObj, false);
        double deltaX = newPosition.getX() - animObj.getCurrentPosition().getX();
        double deltaY = newPosition.getY() - animObj.getCurrentPosition().getY();
        double deltaZ = newPosition.getZ() - animObj.getCurrentPosition().getZ();

        int frames = seconds * animationFPS;

        for(int x = 1; x <= frames; x++) {
            Translate point = new Translate(deltaX / frames, deltaY / frames, deltaZ / frames);
            newAnim.addAnimationPoint(x, new AnimationPoint(point));
        }
        addToAnimationsTrans(newAnim);
    }

    public void removeAnimation(AnimationController animCon) {
        rotateAnimations.remove(animCon);
        translateAnimations.remove(animCon);
    }

    public void createRotateToAnimation(Animatable animObj, double xAngle, double yAngle, int seconds, boolean b) {
        AnimationController newAnim = new AnimationController(animObj, b);
        double deltaX = xAngle - animObj.getCurrentXAngle();
        double deltaY = yAngle - animObj.getCurrentYAngle();

        int frames = seconds * animationFPS;

        for(int x = 1; x <= frames; x++) {
            newAnim.addAnimationPoint(x, new AnimationPoint(deltaX / frames, deltaY / frames));
        }
        addToAnimationsRot(newAnim);
    }
}
