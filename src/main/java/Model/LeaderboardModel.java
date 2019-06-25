package Model;

import Observable.LeaderboardObservable;
import Observer.LeaderboardObserver;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardModel implements LeaderboardObservable {

    private String place1;
    private String place2;
    private String place3;
    private String points1;
    private String points2;
    private String points3;

    private String waarde = "";

    private List<LeaderboardObserver> lijst = new ArrayList<>();

    public void addValue(){
        waarde="groetjes";
        notifyAllObs();
    }


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
    public String getWaarde() {
        return waarde;
    }

    @Override
    public String getPlace1() {
        return place1;
    }

    @Override
    public String getPlace2() {
        return place2;
    }

    @Override
    public String getPlace3() {
        return place3;
    }

    @Override
    public String getValue1() {
        return points1;
    }

    @Override
    public String getValue2() {
        return points2;
    }

    @Override
    public String getValue3() {
        return points3;
    }


    public void playerValue(String place1, String place2, String place3) {

        this.place1 = place1;
        this.place2 = place2;
        this.place3 = place3;
        notifyAllObs();

    }

    public void playerPoints(String value1, String value2, String value3) {
        this.points1 = value1;
        this.points2 = value2;
        this.points3 = value3;
        notifyAllObs();
    }
}
