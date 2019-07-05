package Model;

import Controller.CombinationController;
import Observable.CombinationInfoObservable;
import Observer.CombinationInfoObserver;

public class CombinationInfoModel implements CombinationInfoObservable {

    private CombinationController currentCombi;
    private CombinationInfoObserver observer;
    public boolean inShop = false;

    public void showCombination(CombinationController combi){
        currentCombi = combi;
        notifyAllObs();
    }

    @Override
    public void register(CombinationInfoObserver ob) {
        observer = ob;
    }

    @Override
    public void notifyAllObs() {
        observer.update(this);
    }

    @Override
    public String getRace() {
        return currentCombi.getRaceName();
    }

    @Override
    public String getPower() {
        return currentCombi.getPower();
    }

    @Override
    public boolean inShop() {
        return inShop;
    }

    public CombinationController getCombi() {
        return currentCombi;
    }
}
