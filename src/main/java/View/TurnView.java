package View;

import Controller.TurnController;
import Observable.TurnObservable;
import Observer.TurnObserver;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Enum.GameViewEnum;

public class TurnView implements TurnObserver {
    private Group group;
    private TurnController turnCon;

    public Pane pane;
    public Text turnField;

    public TurnView(TurnController turnCon){
        this.group = GameViewEnum.TURN.getGroup();
        this.turnCon = turnCon;
    }

    public void initialize() {
        group.getChildren().add(pane);
        turnCon.register(this);
    }

    private void setTextFields(String name){
        turnField.setText("Turn: " + name);
    }

    @Override
    public void update(TurnObservable to) {
        setTextFields(to.getPlayerName());
    }
}
