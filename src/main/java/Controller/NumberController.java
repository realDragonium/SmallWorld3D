package Controller;

import Managers.AnimationsManager;
import Model.NumberModel;
import Objects.Animatable;
import Objects.AnimationPoint;
import Observer.NumberObserver;
import javafx.scene.transform.Translate;

public class NumberController implements Animatable {
    private NumberModel model = new NumberModel();

    public NumberController(){
        AnimationsManager.getInstance().createRotateToAnimation(this, 0, 360, 6, true);
    }

    public void setNumber(int number){
        model.setNumber(number);
    }

    public void registerObserver(NumberObserver obs){
        model.register(obs);
    }

    @Override
    public void resetToOrigin(AnimationPoint animPoint) {

    }

    @Override
    public void doAnimation(AnimationPoint animPoint) {
        model.rotateNumber(animPoint.getYAngle());
    }

    @Override
    public Translate getCurrentPosition() {
        return model.getPosition();
    }

    @Override
    public double getCurrentXAngle() {
        return 0;
    }

    @Override
    public double getCurrentYAngle() {
        return model.getYAngle();
    }
}
