package Model;

import Observable.TimerObservable;
import Observer.TimerObserver;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TimerModel implements TimerObservable, ChangeListener {

    private int elapsedTime = 0;
    private TimerObserver observer;
    private int timeAmount;
    private boolean timerDone = false;
    private boolean myTurn;

    public TimerModel(){

    }

    public boolean timerIsDone(){
        return timerDone;
    }

    @Override
    public void register(TimerObserver to) {
        observer = to;
    }

    @Override
    public int getSeconds() {
        return timeAmount;
    }

    @Override
    public void notifyAllObservers() {
        observer.update(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }


    public void setTimer(int timer) {
        timeAmount = timer;
        if(timeAmount >= 0) notifyAllObservers();
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public boolean isMyTurn() {
        return myTurn;
    }
}
