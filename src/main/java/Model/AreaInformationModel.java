package Model;

import Controller.AreaController;
import Observable.AreaInformationObservable;
import Observer.AreaInformationObserver;

public class AreaInformationModel implements AreaInformationObservable {

    AreaInformationObserver observer;
    AreaController area;
    boolean active = false;


    public void setActive(){
        active = true;
        notifyAllObs();
    }

    public void setNonActive(){
        active = false;
        notifyAllObs();
    }

    public void setArea(AreaController area){
        this.area = area;
        notifyAllObs();
    }

    @Override
    public void register(AreaInformationObserver ob) {
        observer = ob;
    }

    @Override
    public void notifyAllObs() {
        observer.update(this);
    }

    @Override
    public AreaController getArea() {
        return area;
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
