package Controller;

import Enums.ApplicationViewEnum;
import Model.LeaderboardModel;
import Observer.LeaderboardObserver;

import java.util.*;


public class LeaderboardController {
    private LeaderboardModel model = new LeaderboardModel();
    private ApplicationController appCon;

    public LeaderboardController(ApplicationController appCon){
        this.appCon = appCon;
    }

    public void sortPlayers(List<PlayerController> players) {
        ArrayList<Integer> pointsList = new ArrayList<>();
        LinkedList<PlayerController> playerPositions = new LinkedList<>();
        for(PlayerController player : players){
            pointsList.add(player.getPoints());
        }
        Collections.sort(pointsList);
        for(PlayerController player : players){
           for(Integer points: pointsList){
               if(player.getPoints() == points)
                   playerPositions.add(player);
           }
        }
        model.players = playerPositions;
        model.notifyAllObs();
    }

    public void back(){
        appCon.setActiveView(ApplicationViewEnum.HOMESCREEN);
    }

    public void registreer(LeaderboardObserver ob){
        model.register(ob);
    }

}
