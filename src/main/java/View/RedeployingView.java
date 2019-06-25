package View;

import Controller.RedeployingController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class RedeployingView {

    public Button remove;
    public Button add;
    Group group;

    public Pane pane;

    private RedeployingController redCon;

    public RedeployingView(Group group, RedeployingController redCon){
        this.redCon = redCon;
        this.group = group;
    }


    public void initialize(){
        group.getChildren().add(pane);
    }

    public void removeButton() {
        redCon.removeFiche();
    }

    public void addButton() {
        redCon.addFiche();
    }
}
