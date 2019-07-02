package View;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class UIPlayer {

    Group group;
    public Pane pane;

    public UIPlayer(Group group) {
        this.group = group;
    }

    public void initialize(){
        group.getChildren().addAll(pane);
    }
}
