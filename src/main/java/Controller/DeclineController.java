package Controller;

import Objects.FXMLLOADER;
import View.DeclineView;
import javafx.scene.Group;

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
        new FXMLLOADER().loader("/DeclineView.fxml", (Callable<DeclineView>) () -> new DeclineView(this));
    }

    private void makeCombinationNonActive(){
        gameCon.getFireBase().declineAction();

    }


}
