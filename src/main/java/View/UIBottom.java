package View;

import Controller.GameController;
import Enums.RaceEnum;
import Observable.PlayerObservable;
import Observer.PlayerObserver;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;


public class UIBottom implements PlayerObserver {

    public Group activeCombi;
    public Group declinedCombi;
    public Text punten;
    public Text fiches;

    Group group;
    public Pane pane;
    private GameController gameCon;

    public UIBottom(Group group, GameController gameCon) {
        this.group = group;
        this.gameCon = gameCon;
    }

    public void initialize(){
        group.getChildren().add(pane);
        TimerTask start = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->getPlayer());
            }
        };

        new Timer().schedule(start, 500);

    }

    private void getPlayer(){
        gameCon.getPlayer(gameCon.getMyPlayerId()).register(this);
    }

    @Override
    public void update(PlayerObservable po) {
        activeCombi.getChildren().clear();
        if(po.hasActiveCombination()){
            Group combi = RaceEnum.valueOf(po.getCurrentCombi().getRaceName()).getGroup();
            activeCombi.getChildren().add(combi);
            fiches.setText(""+po.getCurrentCombi().getFichesAmount());
        } else {
            fiches.setText("");
        }
        if(po.getDeclineCombies().size() > 0 &&declinedCombi.getChildren().size() == 0 ){
            Group combi = RaceEnum.valueOf(po.getDeclineCombies().get(0).getRaceName()).getGroup();
            declinedCombi.getChildren().add(combi);
        }
        punten.setText(""+po.getPoints());
    }
}
