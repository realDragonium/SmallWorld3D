package Model;


import Observable.DiceObservable;
import Observer.DiceObserver;

import java.util.ArrayList;
import java.util.List;

public class DiceModel implements DiceObservable {

    private int gegooideOgen = 0;
    private int waarde;
    boolean playing = false;


    List<DiceObserver> observers = new ArrayList<>();


    public void changeSide(int gegooideOgen) {
        this.gegooideOgen = gegooideOgen;

        notifyAllObs();
    }

    public int giveValue(int gegooideOgen) {
        if (gegooideOgen < 3) {
            return 0;
        }
        return gegooideOgen - 3;

    }

    public void register(DiceObserver ob) {
        observers.add(ob);
    }


    @Override
    public void notifyAllObs() {
        for (DiceObserver ob : observers) {
            ob.update(this);
        }
    }

    @Override
    public int getWaarde() {
        return gegooideOgen;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    public void play(int uitkomst) {
        playing = true;
        gegooideOgen = uitkomst;
        notifyAllObs();
    }
}

