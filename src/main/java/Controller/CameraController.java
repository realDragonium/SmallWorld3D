package Controller;

import Managers.AnimationsManager;
import Model.CameraModel;
import Objects.Animatable;
import Objects.AnimationPoint;
import Observer.CameraObserver;
import javafx.scene.transform.Translate;

public class CameraController implements Animatable {

    CameraModel model = new CameraModel();

    public CameraController(ApplicatieController applicatieController){

    }


    public void registerObserver(CameraObserver co){
        model.register(co);
    }

    public void moveToPosition(Translate translate){
        AnimationsManager.getInstance().createMoveToAnimation(this, translate);
    }

    @Override
    public void resetToOrigin(AnimationPoint animPoint) {
        model.setPosition(animPoint.getTranslate());
        model.setRotation(animPoint.getXAngle(), animPoint.getYAngle());
    }

    @Override
    public void doAnimation(AnimationPoint animPoint) {
        model.moveCamera(animPoint.getTranslate());
        model.rotateCamera(animPoint.getXAngle(), animPoint.getYAngle());
    }

    @Override
    public Translate getCurrentPosition() {
        return model.getTranslate();
    }
}
