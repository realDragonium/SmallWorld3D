package Special.AttackPhase;

import Controller.AreaController;
import Controller.CombinationController;
import Controller.FirebaseGameController;
import Controller.GameController;
import Enums.NotificationEnum;
import Special.SpecialAction;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class BerserkAction implements SpecialAction, AttackPhase {

    @Override
    public void doAction(GameController gameCon, AreaController area) {
        FirebaseGameController fbGame = gameCon.getFireBase();
        final CombinationController combi = gameCon.getTurnCon().getCurrentCombi();
        int eyes = gameCon.getDiceCon().rollDice();
        gameCon.getFireBase().diceAction(eyes, area.getId());
        int needed = combi.fichesNeeded(area) - eyes;
        if(needed > combi.getFichesAmount()) {
            gameCon.setMessage(NotificationEnum.NOTENOUGHFICHES);
            return;
        }
        if(needed < 1) needed = 1;
        int fiches = needed;

        TimerTask delayedAttack = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    fbGame.specificAttackAction(area.getId(), fiches);
                });
            }
        };
        new Timer().schedule(delayedAttack, 4000);
    }
}
