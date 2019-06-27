package Model;

import Enum.ApplicationViewEnum;
import Observable.ApplicationObservable;
import Observer.ApplicationObserver;
import javafx.scene.SubScene;

public class ApplicationModel implements ApplicationObservable {

    private ApplicationObserver observer;
    private ApplicationViewEnum currentView;
    private SubScene subScene3d;


    public void setCurrentView(ApplicationViewEnum view){
        currentView = view;
        notifyObserver();
    }

    @Override
    public void register(ApplicationObserver ao) {
        observer = ao;
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }

    @Override
    public ApplicationViewEnum getCurrentView() {
        return currentView;
    }

}
