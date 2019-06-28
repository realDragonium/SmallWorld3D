package FirebaseActions;

import Controller.GameTurn;

public class NextPhase implements  FirebaseTurnAction {

    @Override
    public void doAction(GameTurn gameTurn) {
        gameTurn.nextPhase();
    }
}
