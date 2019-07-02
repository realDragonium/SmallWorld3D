package Controller;

import Objects.SpecialFXMLLoader;
import View.DeclineView;

import java.util.concurrent.Callable;

public class DeclineController {

    GameController gameCon;

    DeclineController(GameController gameCon){
        this.gameCon = gameCon;
    }


    public void inVerval() {
        makeCombinationNonActive();
        createDeclineView();
    }

    private void createDeclineView() {
        new SpecialFXMLLoader().loader("/DeclineView.fxml", (Callable<DeclineView>) () -> new DeclineView(this));
    }

    private void makeCombinationNonActive(){
        gameCon.getFireBase().declineAction();

    }


}
