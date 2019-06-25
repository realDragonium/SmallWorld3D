package Model;

import Observable.CameraObservable;
import Observer.CameraObserver;
import javafx.scene.transform.Translate;


public class CameraModel implements CameraObservable {

    CameraObserver observer;

    Translate currentPosition = new Translate(0,0,0);
    int xAngle = 0;
    int yAngle = 0;

    public void setPosition(Translate position){
        currentPosition = position;
        notifyObserver();
    }

    public void setRotation(int xAngle, int yAngle){
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

    public void rotateCamera(int deltaX, int deltaY){
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
    public int getXAngle() {
        return xAngle;
    }

    @Override
    public int getYAngle() {
        return yAngle;
    }

    @Override
    public Translate getTranslate() {
        return currentPosition;
    }
}
