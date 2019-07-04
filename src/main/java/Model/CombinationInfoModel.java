package Model;

import Controller.CombinationController;
import Observable.CombinationInfoObservable;
import Observer.CombinationInfoObserver;

public class CombinationInfoModel implements CombinationInfoObservable {

    private CombinationController currentCombi;
    private boolean showing = false;
    private CombinationInfoObserver observer;

    public void showCombination(CombinationController combi){
        currentCombi = combi;
        showing = true;
        notifyAllObs();
    }

    public void hideInfoScreen(){
        showing = false;
        notifyAllObs();
    }

    @Override
    public void register(CombinationInfoObserver ob) {
        observer = ob;
        notifyAllObs();
    }

    @Override
    public void notifyAllObs() {
        observer.update(this);
    }

    @Override
    public boolean showing() {
        return showing;
    }

    @Override
    public String getRace() {
        return currentCombi.getRace();
    }

    @Override
    public String getPower() {
        return currentCombi.getPower();
    }

    public CombinationController getCombi() {
        return currentCombi;
    }
}
