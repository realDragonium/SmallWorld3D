package FirebaseActions;

import Controller.GameController;

public class NextPhase implements FirebaseTurnAction {

    @Override
    public void doAction(GameController gameCon) {
        gameCon.nextPhase();
    }
}
