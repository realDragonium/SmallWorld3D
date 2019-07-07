package Controller;

import Enums.ApplicationViewEnum;
import Model.LeaderboardModel;
import Observer.LeaderboardObserver;

import java.util.*;


public class LeaderboardController {
    private LeaderboardModel model = new LeaderboardModel();
    private ApplicationController appCon;

    public LeaderboardController(ApplicationController appCon) {
        this.appCon = appCon;
    }

    public void sortPlayers(List<PlayerController> players) {
        ArrayList<Integer> pointsList = new ArrayList<>();
        LinkedList<PlayerController> playerPositions = new LinkedList<>();
        for (PlayerController player : players) {
            pointsList.add(player.getPoints());
        }
        System.out.println(pointsList);
        Collections.sort(pointsList);
        Collections.reverse(pointsList);
        System.out.println(pointsList);
        for (Integer points : pointsList) {
            for (PlayerController player : players) {
                if (player.getPoints() == points)
                    playerPositions.add(player);
            }
        }
        setTop3(playerPositions);
    }

    private void setTop3(LinkedList<PlayerController> players) {
        List<String> names = new ArrayList<>();
        List<Integer> points = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            names.add(players.get(i).getName());
            points.add(players.get(i).getPoints());
        }
        model.names = names;
        model.points = points;
        model.notifyAllObs();
    }

    public void back() {
        appCon.setActiveView(ApplicationViewEnum.HOMESCREEN);
    }

    public void register(LeaderboardObserver ob) {
        model.register(ob);
    }

}
