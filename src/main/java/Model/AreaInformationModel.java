package Model;

import Controller.AreaController;
import Observable.AreaInformationObservable;
import Observer.AreaInformationObserver;

public class AreaInformationModel implements AreaInformationObservable {

    private AreaInformationObserver observer;
    private AreaController area;

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

}
