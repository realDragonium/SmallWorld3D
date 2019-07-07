package Model;

import Observable.NumberObservable;
import Observer.NumberObserver;
import javafx.scene.transform.Translate;

import java.util.ArrayList;

public class NumberModel implements NumberObservable {

    ArrayList<NumberObserver> observer = new ArrayList<>();
    int currentNumber;
    Translate position;
    double yAngle;

    public void setNumber(int number){
        currentNumber = number;
        notifyAllObs();
    }


    @Override
    public void register(NumberObserver ob) {
        observer.add(ob);
    }

    @Override
    public void notifyAllObs() {
        for(NumberObserver observer : observer)
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
