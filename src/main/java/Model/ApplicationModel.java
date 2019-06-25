package Model;

import Enum.ApplicatieViewEnum;
import Observable.ApplicatieObservable;
import Observer.ApplicatieObserver;
import javafx.scene.SubScene;

import java.util.List;

public class ApplicationModel implements ApplicatieObservable {

    private ApplicatieObserver observer;
    private ApplicatieViewEnum currentView;
    private SubScene subScene3d;


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

    @Override
    public SubScene get3dScene() {
        return subScene3d;
    }

    public void addSubScene(SubScene subScene) {
        subScene3d = subScene;
        notifyObserver();
    }
}
