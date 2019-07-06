package View;

import Controller.DeclineController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import Enums.GameViewEnum;

public class DeclineView {

    Group group;
    public Pane root;

    private DeclineController declineCon;

    public DeclineView(DeclineController declineCon){
        this.declineCon = declineCon;
        this.group = GameViewEnum.DECLINE.getGroup();
    }

    public void initialize(){
        group.getChildren().addAll(root);
    }

    public void InDecline(){
        declineCon.InDecline();
    }

    public void closeView(){
        declineCon.notInDecline();
    }
}