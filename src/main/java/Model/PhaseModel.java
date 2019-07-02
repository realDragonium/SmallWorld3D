package Model;

import Observable.PhaseObservable;
import Observer.PhaseObserver;
import Phase.Phase;
import java.util.ArrayList;
import java.util.List;
import Enums.PhaseEnum;
import Phase.SpectatePreparing;

public class PhaseModel implements PhaseObservable {
    private List<PhaseObserver> observers = new ArrayList<>();
    private Phase phase = new SpectatePreparing();

    public PhaseModel(){

    }

    public void setPhase(PhaseEnum phase){
        this.phase = phase.getPhase();
        notifyObserver();
    }

    @Override
    public void register(PhaseObserver po) {
        observers.add(po);
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        observers.forEach((po) -> po.update(this));
    }

    @Override
    public Phase getPhase() {
        return phase;
    }
}
