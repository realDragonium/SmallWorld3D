package View;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import Enum.GameViewEnum;
public class UIView {

    Group group;
    public Pane pane;

    public UIView() {
        this.group = GameViewEnum.UIOVERLAY.getGroup();
    }

    public void initialize(){
        group.getChildren().add(pane);
    }
}
