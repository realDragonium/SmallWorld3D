package Model;

import Observable.CameraObservable;
import Observer.CameraObserver;
import javafx.scene.transform.Translate;


public class CameraModel implements CameraObservable {

    CameraObserver observer;

    Translate currentPosition = new Translate(0,0,0);
    double xAngle = 0;
    double yAngle = 0;

    public void setPosition(Translate position){
        currentPosition = position;
        notifyObserver();
    }

    public void setRotation(double xAngle, double yAngle){
        this.xAngle = xAngle;
        this.yAngle = yAngle;
        notifyObserver();
    }

    public void moveCamera(Translate deltaPosition){
        currentPosition.setX(currentPosition.getX() + deltaPosition.getX());
        currentPosition.setY(currentPosition.getY() + deltaPosition.getY());
        currentPosition.setZ(currentPosition.getZ() + deltaPosition.getZ());
        notifyObserver();
    }

    public void rotateCamera(double deltaX, double deltaY){

        xAngle += deltaX;
        yAngle += deltaY;
        notifyObserver();
    }

    @Override
    public void register(CameraObserver ao) {
        observer = ao;
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }

    @Override
    public double getXAngle() {
        return xAngle;
    }

    @Override
    public double getYAngle() {
        return yAngle;
    }

    @Override
    public Translate getTranslate() {
        return currentPosition;
    }
}
