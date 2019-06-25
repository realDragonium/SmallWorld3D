package Controller;

import Managers.AnimationsManager;
import Model.CameraModel;
import Objects.Animatable;
import Objects.AnimationPoint;
import Observer.CameraObserver;
import javafx.scene.transform.Translate;

public class CameraController implements Animatable {

    CameraModel model = new CameraModel();
    Controller3D worldCon;

    public CameraController(Controller3D worldCon){
        this.worldCon = worldCon;
        //AnimationsManager.getInstance().createUpAndDownAnimation(this, -10, 5);
        moveToPosition(new Translate(-1500, -1500, 0), 12);
        rotateToAngle(-50, 90, 8);
    }


    public void registerObserver(CameraObserver co){
        model.register(co);
    }

    public void moveToPosition(Translate translate, int seconds){
        AnimationsManager.getInstance().createMoveToAnimation(this, translate, seconds);
    }

    public void rotateToAngle(int xAngle, int yAngle, int seconds){
        AnimationsManager.getInstance().createRotateToAnimation(this, xAngle, yAngle, seconds);
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

    @Override
    public double getCurrentXAngle() {
        return model.getXAngle();
    }

    @Override
    public double getCurrentYAngle() {
        return model.getYAngle();
    }
}
