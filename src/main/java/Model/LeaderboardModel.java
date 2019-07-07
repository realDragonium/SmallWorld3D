package Model;

import Controller.PlayerController;
import Observable.LeaderboardObservable;
import Observer.LeaderboardObserver;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardModel implements LeaderboardObservable {

    public List<PlayerController> players;

    private List<LeaderboardObserver> lijst = new ArrayList<>();


    @Override
    public void register(LeaderboardObserver ob) {
        lijst.add(ob);
    }

    @Override
    public void notifyAllObs() {
        for(LeaderboardObserver ob : lijst){
            ob.update(this);
        }
    }

    @Override
    public List<PlayerController> getPlayers() {
        return players;
    }

}
