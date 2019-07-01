package View;

import Controller.DeclineController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import Enum.GameViewEnum;

public class DeclineView {


    public Button declineButton;
    Group group;
    public Pane root;

    private DeclineController declineCon;
    private TextField declinetext;

    public DeclineView(DeclineController declineCon){
        this.declineCon = declineCon;
        this.group = GameViewEnum.DECLINE.getGroup();
    }

    public void initialize(){
        group.getChildren().addAll(root);
    }

    public void inVerval(){declineCon.inVerval();}

}