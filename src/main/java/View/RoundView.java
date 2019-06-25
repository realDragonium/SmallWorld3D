package View;

import Controller.RoundController;
import Observable.RoundObservable;
import Observer.RoundObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.text.Text;


/**
 * @author : Martijn van der Steen
 * @version : Juni 2019
 */

public class RoundView implements RoundObserver {



    private Group group;
    private RoundController roundCon;

    @FXML
    public Text roundField;
    public Group groupFXML;

    public RoundView(Group group, RoundController roundCon){
        this.group = group;
        this.roundCon = roundCon;
    }


    /**
     * In the method initialize
     */

    public void initialize() {
        group.getChildren().add(groupFXML);
        roundCon.register(this);
        roundField.setX(20);
        roundField.setY(35);
    }
    @Override
    public void update(RoundObservable ro) {
        setTextRoundField(ro.getRound());
    }


    /**
     * @param getal is meegegeven integer om de juiste ronde te zetten.
     */
    private void setTextRoundField(int getal){
        roundField.setText("Round: "+ getal);
    }

}
