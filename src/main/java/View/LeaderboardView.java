package View;

import Controller.LeaderboardController;
import Observable.LeaderboardObservable;
import Observer.LeaderboardObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class LeaderboardView implements LeaderboardObserver {
    private LeaderboardController leadCon;

    public Label place1;
    public Label place2;
    public Label place3;
    public Label points1;
    public Label points2;
    public Label points3;

    public Group root;
    private Group group;
    public Button hoofdmenu;

    public LeaderboardView(LeaderboardController leadCon, Group group){
        this.group = group;
        this.leadCon = leadCon;
    }

    public void initialize(){
        group.getChildren().add(root);
        leadCon.registreer(this);
    }

    @Override
    public void update(LeaderboardObservable ob) {
        for (int i = 0; i < 3; i++) {

        }

    }

    @FXML
    public void backButton(){

    }
}
