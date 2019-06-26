package Model;

import Observable.FicheObservable;
import Observer.FicheObserver;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;

public class FicheModel implements FicheObservable {

    Translate position = new Translate(0,0,0);
    List<FicheObserver> observers = new ArrayList<>();

    public void setPosition(Translate translate){
        position = translate;
    }

    public void moveFiche(Translate deltaPosition){
        position.setX(position.getX() + deltaPosition.getX());
        position.setY(position.getY() + deltaPosition.getY());
        position.setZ(position.getZ() + deltaPosition.getZ());
        notifyAllObs();
    }

    @Override
    public void register(FicheObserver ob) {
        observers.add(ob);
    }

    @Override
    public void notifyAllObs() {
        for(FicheObserver obs : observers){
            obs.update(this);
        }
    }

    @Override
    public Translate getPosition() {
        return position;
    }
}
