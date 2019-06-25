package View;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class gameLogoView {

    Group group;
    public Pane pane;

    public gameLogoView(Group group){
        this.group = group;
    }

    public void initialize(){
        group.getChildren().add(pane);
    }
}
