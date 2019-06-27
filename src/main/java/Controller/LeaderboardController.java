package Controller;

import Managers.SceneManager;
import Model.LeaderboardModel;
import Observer.LeaderboardObserver;

import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class LeaderboardController {
    private LeaderboardModel leaderboardModel = new LeaderboardModel();




    public void addValue() {
        leaderboardModel.addValue();
    }

    public void registreer(LeaderboardObserver ob){
        leaderboardModel.register(ob);
    }

    private void changePlayer(String place1,String place2, String place3){

        leaderboardModel.playerValue(place1, place2, place3);
    }

    private void changePoints(String value1, String value2, String value3){
        leaderboardModel.playerPoints(value1, value2, value3);
    }



}
