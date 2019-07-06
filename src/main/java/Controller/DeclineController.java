package Controller;

import Enums.GameViewEnum;
import Objects.SpecialFXMLLoader;
import View.DeclineView;

import java.util.concurrent.Callable;

public class DeclineController {

    GameController gameCon;

    DeclineController(GameController gameCon){
        this.gameCon = gameCon;
        createDeclineView();
    }

    private void createDeclineView() {
        new SpecialFXMLLoader().loader("/DeclineView.fxml", (Callable<DeclineView>) () -> new DeclineView(this));
    }

    public void InDecline() {
        closeDeclineView();
        gameCon.getFireBase().declineAction();
    }

    private void closeDeclineView(){
        gameCon.removeFromGameView(GameViewEnum.DECLINE);
    }

    public void notInDecline(){
        closeDeclineView();
        CombinationController combi = gameCon.getTurnCon().getCurrentCombi();
        combi.checkPrepareAreas();
        combi.prepareRound();
        gameCon.addToGameView(GameViewEnum.BUTTON);
    }

}
