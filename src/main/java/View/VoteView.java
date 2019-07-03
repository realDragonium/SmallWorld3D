package View;

import Controller.VoteController;
import Enums.GameViewEnum;
import Observable.VoteObservable;
import Observer.VoteObserver;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class VoteView implements VoteObserver {

    public Text message;
    public Pane pane;
    public Button vote;
    public Group votes;
    public Group voted;

    VoteController voteCon;
    Group group;

    public VoteView(VoteController voteCon){
        this.voteCon = voteCon;
        this.group = GameViewEnum.VOTE.getGroup();
    }

    public void initialize() {
        group.getChildren().add(pane);
        voteCon.registerObserver(this);
    }

    public void clickVote(){
        voteCon.doVote();
    }

    @Override
    public void update(VoteObservable vo) {
        System.out.println("update!");
        vote.setVisible(!vo.hasVoted());
        for(int i = 0; i < vo.getVotesNeeded(); i++){
            votes.getChildren().get(i).setVisible(true);
        }
        for(int i = 0; i < vo.getCurrentVotes(); i++){
            voted.getChildren().get(i).setVisible(true);
        }
        message.setText(vo.getMessage());
    }
}
