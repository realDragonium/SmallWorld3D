package Controller;

import Model.RoundModel;
import Objects.FXMLLOADER;
import Observer.RoundObserver;
import View.RoundView;
import javafx.scene.Group;

import java.util.concurrent.Callable;


public class RoundController {

    private RoundModel model;
    private GameController gameCon;

    RoundController(GameController gameCon){
        model = new RoundModel(8);
        this.gameCon = gameCon;
        createRoundView();
    }

    private void createRoundView() {
        new FXMLLOADER().loader("/RoundView.fxml", (Callable<RoundView>)() -> new RoundView(this));
    }


    void nextRound() {
        if(model.currentRound >= model.getMaxRounds()) {
            gameCon.endGame();
            return;
        }
        gameCon.getTurnCon().newRound();
        model.nextRound();
    }

    public int getCurrentRound(){
        return model.currentRound;
    }

    public void register(RoundObserver ro ){
        model.register(ro);
    }
}
