package View;

import Controller.CameraController;
import Objects.Xform;
import Observable.CameraObservable;
import Observer.CameraObserver;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import Enums.View3DEnum;

public class CameraView implements CameraObserver {
    Group root;
    Xform xForm = new Xform();
    Xform cameraXform3 = new Xform();
    PerspectiveCamera camera;
    CameraController cameraCon;

    public CameraView(CameraController cameraCon){
        this.cameraCon = cameraCon;
        this.root = View3DEnum.CAMERA.getGroup();
        buildCamera();
        cameraCon.registerObserver(this);
    }

    private void buildCamera(){
        camera = new PerspectiveCamera(true);
        final Xform cameraXform2 = new Xform();
        cameraXform3 = new Xform();
        xForm.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);

        camera.setFieldOfView(70);
        camera.setNearClip(0.1);
        camera.setFarClip(10000);

        root.getChildren().add(xForm);
    }

    @Override
    public void update(CameraObservable co){
        xForm.relocate(co.getTranslate().getX(), co.getTranslate().getY());
        xForm.setTranslateZ(co.getTranslate().getZ());
        xForm.setRotate(co.getXAngle(), co.getYAngle(), 0);
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }
}
