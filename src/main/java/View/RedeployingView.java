package View;

import Controller.RedeployingController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import Enums.GameViewEnum;

public class RedeployingView {
    public Button add;
    public Button remove;
    private Group group;

    public Pane pane;

    private RedeployingController redCon;

    public RedeployingView(RedeployingController redCon){
        this.redCon = redCon;
        this.group = GameViewEnum.REDEPLOY.getGroup();
    }


    public void initialize(){
        group.getChildren().add(add);
        group.getChildren().add(remove);
    }

    public void removeButton() {
        redCon.removeFiche(remove.getId());
    }

    public void addButton() {
        redCon.addFiche(add.getId());
    }
}
