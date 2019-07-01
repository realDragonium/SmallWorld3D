package Controller;

import Enum.NotificationEnum;
import Objects.FXMLLOADER;
import View.ButtonView;

import java.util.concurrent.Callable;

public class ButtonController {
    private GameController gameCon;

    ButtonController(GameController gameCon){
        this.gameCon = gameCon;
        createButtonView();
    }

    private void createButtonView() {
        new FXMLLOADER().loader("/ButtonView.fxml", (Callable<ButtonView>) () -> new ButtonView(this));
    }

    public void showInfo() {

    }

    public void nextPhase(){
        gameCon.getFireBase().nextPhaseAction();
    }

    public void createShopItem() {
        gameCon.createRandomShopItem();
    }

    public void previewNotification() {
        gameCon.setMessage(NotificationEnum.DRAGON);
    }

    public void activeListener() {
        gameCon.getFireBase().activateListener();
    }
}
