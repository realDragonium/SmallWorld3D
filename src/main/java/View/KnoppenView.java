package View;

import Controller.KnoppenController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class KnoppenView {

    private Group root;
    private KnoppenController knopCon;

    @FXML
    public Pane pane;
    public Button infoButton;
    public Button availableButton;
    public Button nextFaseButton;

    public KnoppenView(Group group, KnoppenController knopCon){
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
