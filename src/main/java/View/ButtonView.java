package View;

import Controller.ButtonController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import Enums.GameViewEnum;
public class ButtonView {

    private Group root;
    private ButtonController buttonCon;

    public Pane pane;
    public Button infoButton;
    public Button availableButton;
    public Button nextFaseButton;

    public ButtonView(ButtonController buttonCon){
        root = GameViewEnum.BUTTON.getGroup();
        this.buttonCon = buttonCon;
    }

    public void initialize() {
        root.getChildren().add(pane);
    }


    public void showInfo(){ buttonCon.showInfo(); }
    public void fichesOver(){
        buttonCon.activeListener();
    }
    public void nextPhase(){ buttonCon.nextPhase(); }
    public void createShopItem() {
        buttonCon.createShopItem();
    }
    public void previewNotification() {
        buttonCon.previewNotification();
    }


}
