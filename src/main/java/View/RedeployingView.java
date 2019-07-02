package View;

import Controller.RedeployingController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import Enums.GameViewEnum;

public class RedeployingView {

    public Button remove;
    public Button add;
    Group group;

    public Pane pane;

    private RedeployingController redCon;

    public RedeployingView(RedeployingController redCon){
        this.redCon = redCon;
        this.group = GameViewEnum.REDEPLOY.getGroup();
    }


    public void initialize(){
        group.getChildren().add(pane);
    }

    public void removeButton() {
        //redCon.removeFiche();
    }

    public void addButton() {
        //redCon.addFiche();
    }
}
