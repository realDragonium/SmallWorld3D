package View;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class UIBottom {

    Group group;
    public Pane pane;

    public UIBottom(Group group) {
        this.group = group;
    }

    public void initialize(){
        group.getChildren().addAll(pane);
    }
}
