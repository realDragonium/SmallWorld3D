package View;

import Controller.ButtonController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ButtonView {

    private Group root;
    private ButtonController buttonCon;

    public Pane pane;
    public Button infoButton;
    public Button availableButton;
    public Button nextFaseButton;

    public ButtonView(Group group, ButtonController buttonCon){
        root = group;
        this.buttonCon = buttonCon;
    }

    public void initialize() {
        root.getChildren().add(pane);
    }


    public void showInfo(){ buttonCon.showInfo(); }
    public void fichesOver(){
        buttonCon.fichesOver();
    }
    public void nextPhase(){ buttonCon.nextPhase(); }
}
