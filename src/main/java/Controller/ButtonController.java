package Controller;

import Managers.SceneManager;

public class ButtonController {

    private GameController gameCon;

    ButtonController(GameController gameCon){
        this.gameCon = gameCon;
//        SceneManager.getInstance().loadButtons(this);
    }

    public void showInfo() {
        SceneManager.getInstance().addToStandardScene("infoGroup");
    }

    public void fichesOver() {
        gameCon.getCurrentPlayer().showActiveCombiFichesLeft();
    }

    public void nextPhase(){
        //gameCon.getGameTurn().endPhase();
         }

}
