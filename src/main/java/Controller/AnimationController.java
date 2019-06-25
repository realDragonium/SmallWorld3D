package Controller;

import Managers.AnimationsManager;
import Model.AnimationModel;
import Objects.Animatable;
import Objects.AnimationPoint;

public class AnimationController{

    Animatable animatingObject;
    AnimationModel model = new AnimationModel();

    public AnimationController(Animatable animObj, boolean loop){
        animatingObject = animObj;
        if(loop){
            model.makeLooping();
        }
    }

    public void nextFrame(){
        model.nextFrame();
        animatingObject.doAnimation(model.getAnimPoint(model.getCurFrame()));
        if(model.animEnded()){
            if(!model.looping()) {
                AnimationsManager.getInstance().removeAnimation(this);
            }
            resetAnimation();
        }
    }

    public void addAnimationPoint(int frame, AnimationPoint animPoint){
        model.addAnimPoint(frame, animPoint);
    }

    public void resetAnimation(){
        model.setToFrameOne();
    }

}
