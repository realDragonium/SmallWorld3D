package View;

import Controller.CameraController;
import Objects.Xform;
import Observable.CameraObservable;
import Observer.CameraObserver;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;


public class CameraView implements CameraObserver {
    Group root;
    Xform xForm = new Xform();
    PerspectiveCamera camera;
    CameraController cameraCon;

    public CameraView(Group group, CameraController cameraCon){
        this.cameraCon = cameraCon;
        root = group;
        buildCamera();
    }

    private void buildCamera(){
        final PerspectiveCamera camera = new PerspectiveCamera(true);
        final Xform cameraXform2 = new Xform();
        final Xform cameraXform3 = new Xform();
        xForm.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);
        camera.setNearClip(0.1);
        camera.setFarClip(10000);

        root.getChildren().add(xForm);
    }

    @Override
    public void update(CameraObservable co){
        camera.setTranslateX(co.getTranslate().getX());
        camera.setTranslateY(co.getTranslate().getY());
        camera.setTranslateZ(co.getTranslate().getZ());
        xForm.ry.setAngle(co.getYAngle());
        xForm.rx.setAngle(co.getXAngle());
    }
}
