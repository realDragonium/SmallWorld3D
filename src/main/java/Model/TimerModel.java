package Model;

import Observable.TimerObservable;
import Observer.TimerObserver;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TimerModel implements TimerObservable, ChangeListener {

    private int elapsedTime;
    private TimerObserver observer;
    private int timeAmount = 10;
    private boolean timerDone = false;

    public TimerModel(){

    }

    public boolean timerIsDone(){
        return timerDone;
    }

    @Override
    public void register(TimerObserver to) {
        System.out.println(to);
        observer = to;
        System.out.println(to);
    }

    @Override
    public int getSeconds() {
        return elapsedTime;
    }

    @Override
    public void notifyAllObservers() {
        System.out.println(observer);
        observer.update(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    public void setTimer(int timer) {
        elapsedTime = timer;
        notifyAllObservers();
    }
}
