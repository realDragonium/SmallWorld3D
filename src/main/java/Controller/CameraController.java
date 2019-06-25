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
        moveToPosition(new Translate(0, -50, -1000), 500);
    }


    public void registerObserver(CameraObserver co){
        model.register(co);
    }

    public void moveToPosition(Translate translate, int frames){
        AnimationsManager.getInstance().createMoveToAnimation(this, translate, frames);
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
