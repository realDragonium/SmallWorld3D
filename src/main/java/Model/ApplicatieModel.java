package Model;

import Enum.ApplicatieViewEnum;
import Observable.ApplicatieObservable;
import Observer.ApplicatieObserver;

import java.util.List;

public class ApplicatieModel implements ApplicatieObservable {

    private ApplicatieObserver observer;
    private ApplicatieViewEnum currentView;


    public void setCurrentView(ApplicatieViewEnum view){
        currentView = view;
        notifyObserver();
    }

    @Override
    public void register(ApplicatieObserver ao) {
        observer = ao;
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }

    @Override
    public ApplicatieViewEnum getCurrentView() {
        return currentView;
    }
}
