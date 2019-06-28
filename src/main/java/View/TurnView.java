package View;

import Controller.TurnController;
import Enum.TurnFase;
import Observable.TurnObservable;
import Observer.TurnObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class TurnView implements TurnObserver {



    private Group group;
    private TurnController turnCon;

    @FXML
    public Group groupFXML;
    public Text turnField;
    public Text faseField;

    public TurnView(Group group, TurnController turnCon){
        this.group = group;
        this.turnCon = turnCon;
    }

    public void initialize() {
        group.getChildren().add(groupFXML);
        turnCon.register(this);
    }

    private void setTextFields(int getal, TurnFase fase){
        turnField.setText("Turn: Player" + getal);
        faseField.setText("" + fase.toString().toUpperCase());
    }

    @Override
    public void update(TurnObservable to) {
        setTextFields(to.getTurn(), to.getFase());
    }
}
