package View;

import Controller.GameController;
import Enums.GameViewEnum;
import Enums.RaceEnum;
import Observable.PlayerObservable;
import Observer.PlayerObserver;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class UIBottom implements PlayerObserver {

    public Group activeCombi;
    public Group declinedCombi;
    public Text punten;

    Group group;
    public Pane pane;
    private GameController gameCon;

    public UIBottom(Group group, GameController gameCon) {

        this.group = group;
        this.gameCon = gameCon;
    }

    public void initialize(){
        group.getChildren().addAll(pane);
        gameCon.getPlayer(gameCon.imPlayer()).register(this);
    }

    @Override
    public void update(PlayerObservable po) {
        System.out.println("got the update");
        activeCombi.getChildren().clear();
        if(po.hasActiveCombination()) {
            activeCombi.getChildren().add(RaceEnum.valueOf(po.getActiveCombi()).getGroup());
        }
    }
}
