package Controller;

import Managers.SceneManager;

public class ButtonController {

    private GameController gameCon;

    ButtonController(GameController gameCon){
        this.gameCon = gameCon;
//        SceneManager.getInstance().loadButtons(this);
    }

    public void showInfo() {

    }

    public void fichesOver() {
        gameCon.getCurrentPlayer().showActiveCombiFichesLeft();
    }

    public void nextPhase(){ gameCon.nextPhase(); }

}
