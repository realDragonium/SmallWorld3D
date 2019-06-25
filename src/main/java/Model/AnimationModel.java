package Model;



import Objects.AnimationPoint;

import java.util.HashMap;
import java.util.Map;

public class AnimationModel {
    Map<Integer, AnimationPoint> animPoints = new HashMap<>();
    int curFrame = 0;
    int lastFrame = 0;

    public void nextFrame(){
        curFrame++;
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
}
