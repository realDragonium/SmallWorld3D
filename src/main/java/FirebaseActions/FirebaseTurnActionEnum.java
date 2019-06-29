package FirebaseActions;

import Controller.GameTurn;
import javafx.application.Platform;

public enum FirebaseTurnActionEnum {
    added(new NextPhase()), bought(new NextPhase()), attacks(new NextPhase()),
    leaves(new NextPhase()), add(new NextPhase()), removes(new NextPhase()),
    nextturn(new NextPhase()), nextround(new NextPhase()), nextphase(new NextPhase()),
    decidestartinplayer(new NextPhase());

    FirebaseTurnAction action;

    FirebaseTurnActionEnum(FirebaseTurnAction action){
        this.action = action;
    }

    public void doAction(GameTurn gameTurn){
        Platform.runLater(() -> action.doAction(gameTurn));
    }
}
