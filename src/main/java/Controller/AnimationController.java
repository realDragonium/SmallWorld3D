package Controller;

import Model.AnimationModel;
import Objects.Animatable;
import Objects.AnimationPoint;

public class AnimationController{

    Animatable animatingObject;
    AnimationModel model = new AnimationModel();

    public AnimationController(Animatable animObj){
        animatingObject = animObj;
    }

    public void nextFrame(){
        model.nextFrame();
        animatingObject.doAnimation(model.getAnimPoint(model.getCurFrame()));
    }

    public void addAnimationPoint(int frame, AnimationPoint animPoint){
        model.addAnimPoint(frame, animPoint);
    }

}
