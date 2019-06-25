package Model;



import Objects.AnimationPoint;

import java.util.HashMap;
import java.util.Map;

public class AnimationModel {
    Map<Integer, AnimationPoint> animPoints = new HashMap<>();
    int curFrame = 0;
    int lastFrame = 0;
    boolean looping = false;

    public void makeLooping(){
        looping = true;
    }

    public boolean looping(){
        return looping;
    }

    public void nextFrame(){
        curFrame++;
    }

    public boolean animEnded(){
        return curFrame >= lastFrame;
    }

    public int getCurFrame(){
        return curFrame;
    }

    public void addAnimPoint(int frame, AnimationPoint animPoint){
        if(frame > lastFrame){
            lastFrame = frame;
        }
        animPoints.put(frame, animPoint);
    }

    public AnimationPoint getAnimPoint(int frame){
        if(animPoints.get(frame) != null){
            return animPoints.get(frame);
        }

        return new AnimationPoint();
    }

    public void setToFrameOne() {
        curFrame = 0;
    }
}
