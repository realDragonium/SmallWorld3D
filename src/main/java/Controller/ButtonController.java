package Controller;

import Enum.NotificationEnum;

public class ButtonController {

    private GameController gameCon;

    ButtonController(GameController gameCon){
        this.gameCon = gameCon;
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
        gameCon.activateFirebase();
    }
}
