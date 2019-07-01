package View;

import Controller.RoundController;
import Observable.RoundObservable;
import Observer.RoundObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.text.Text;
import Enum.GameViewEnum;
public class RoundView implements RoundObserver {



    private Group group;
    private RoundController roundCon;

    @FXML
    public Text roundField;
    public Group groupFXML;

    public RoundView(RoundController roundCon){
        this.group = GameViewEnum.ROUND.getGroup();
        this.roundCon = roundCon;
    }

    public void initialize() {
        group.getChildren().add(groupFXML);
        roundCon.register(this);
    }

    @Override
    public void update(RoundObservable ro) {
        setTextRoundField(ro.getRound());
    }

    private void setTextRoundField(int getal){
        roundField.setText("Round: "+ getal);
    }

}
