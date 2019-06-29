package Controller;

import FirebaseActions.FirebaseAction;
import FirebaseActions.FirebaseTurnActionEnum;

public class ButtonController {

    private GameController gameCon;

    ButtonController(GameController gameCon){
        this.gameCon = gameCon;
    }

    public void showInfo() {

    }

    public void fichesOver() {
        gameCon.getCurrentPlayer().showActiveCombiFichesLeft();
    }

    public void nextPhase(){
//        gameCon.nextPhase();
        FirebaseAction action = new FirebaseAction(FirebaseTurnActionEnum.nextphase);
        gameCon.getFireBase().placeTurnAction(action);
    }

    public void nextTurn() {
    }

    public void nextRound() {

    }
}
