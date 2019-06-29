package FirebaseActions;

import Controller.GameTurn;

public class FirebaseAction {

    private FirebaseTurnActionEnum action;

    public FirebaseAction(){ }

    public FirebaseAction(FirebaseTurnActionEnum action){
        this.action = action;
    }

    public FirebaseTurnActionEnum getAction(){ return action; }

    public void doAction(GameTurn gameTurn){
        action.doAction(gameTurn);
    }


}
