package View;

import Controller.RedeployingController;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class RedeployingView {

    Group group;

    public Pane pane;

    private RedeployingController redCon;

    public RedeployingView(RedeployingController redCon, Group group){
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
