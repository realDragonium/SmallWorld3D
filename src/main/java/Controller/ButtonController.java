package Controller;

import Enum.NotificationEnum;

public class ButtonController {

    private GameController gameCon;

    ButtonController(GameController gameCon){
        this.gameCon = gameCon;
    }

    public void showInfo() {

    }

    public void fichesOver() {
        //gameCon.getCurrentPlayer().showActiveCombiFichesLeft();
    }

    public void nextPhase(){
        gameCon.getFireBase().nextPhaseAction();
    }

    public void nextTurn() {
        gameCon.createRandomShopItem();
    }

    public void nextRound() {
        gameCon.setMessage(NotificationEnum.DRAGON);
    }
}
