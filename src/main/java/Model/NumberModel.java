package Model;

import Observable.NumberObservable;
import Observer.NumberObserver;
import javafx.scene.transform.Translate;

public class NumberModel implements NumberObservable {

    NumberObserver observer;
    int currentNumber;
    Translate position;
    double yAngle;

    public void setNumber(int number){
        currentNumber = number;
        notifyAllObs();
    }


    @Override
    public void register(NumberObserver ob) {
        observer = ob;
    }

    @Override
    public void notifyAllObs() {
        observer.update(this);
    }

    @Override
    public Translate getPosition() {
        return null;
    }

    @Override
    public double getYAngle() {
        return yAngle;
    }

    @Override
    public int getCurNumber() {
        return currentNumber;
    }

    public void rotateNumber(double yAngle) {
        this.yAngle += yAngle;
        notifyAllObs();
    }

    public void setPosition(Translate pos){
        position = pos;
    }
}
