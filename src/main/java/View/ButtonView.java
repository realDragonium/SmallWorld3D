package View;

import Controller.ButtonController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ButtonView {

    private Group root;
    private ButtonController knopCon;

    @FXML
    public Pane pane;
    public Button infoButton;
    public Button availableButton;
    public Button nextFaseButton;

    public ButtonView(Group group, ButtonController knopCon){
        root = group;
        this.knopCon = knopCon;
    }

    public void initialize() {
        root.getChildren().add(pane);
    }

    @FXML
    public void showInfo(){knopCon.showInfo();}
    public void fichesOver(){
        knopCon.fichesOver();
    }
    public void nextPhase(){knopCon.nextPhase();}
}
