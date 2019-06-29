package FirebaseActions;

import Controller.GameController;

public class FirebaseAction {

    private FirebaseTurnActionEnum action;

    public FirebaseAction(){ }

    public FirebaseAction(FirebaseTurnActionEnum action){
        this.action = action;
    }

    public FirebaseTurnActionEnum getAction(){ return action; }

    public void doAction(GameController gameCon){
        action.doAction(gameCon);
    }


}
