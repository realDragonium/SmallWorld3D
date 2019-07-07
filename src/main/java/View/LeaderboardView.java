package View;

import Controller.LeaderboardController;
import Observable.LeaderboardObservable;
import Observer.LeaderboardObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class LeaderboardView implements LeaderboardObserver {
    private LeaderboardController leadCon;

    public Group root;
    private Group group;
    public Button hoofdmenu;

    public LeaderboardView(LeaderboardController leadCon, Group group){
        this.group = group;
        this.leadCon = leadCon;
    }

    public void initialize(){
        group.getChildren().add(root);
        leadCon.register(this);
    }

    @Override
    public void update(LeaderboardObservable ob) {
        for (int i = 0; i < 3; i++) {
            Group group = (Group) root.getChildren().get(i+1);
            ((Label)group.getChildren().get(0)).setText(ob.getPlayerNames().get(i));
            ((Label)group.getChildren().get(1)).setText(""+ob.getPoints().get(i));
        }
    }

    @FXML
    public void backButton(){
        leadCon.back();
    }
}
