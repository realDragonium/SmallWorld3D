package Model;

import Observable.LeaderboardObservable;
import Observer.LeaderboardObserver;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardModel implements LeaderboardObservable {

    public List<String> names;
    public List<Integer> points;

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
    public List<String> getPlayerNames() {
        return names;
    }

    @Override
    public List<Integer> getPoints() {
        return points;
    }


}
