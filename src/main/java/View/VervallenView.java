package View;

import Controller.VervallenController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class VervallenView {


    public Button vervalButton;
    Group group;
    public Pane root;

    private VervallenController vervalCon;
    private TextField vervaltext;

    public VervallenView(Group group, VervallenController vervalCon){
        this.vervalCon = vervalCon;
        this.group = group;
    }

    public void initialize(){
        group.getChildren().addAll(root);
    }

    public void inVerval(){vervalCon.inVerval();}

}