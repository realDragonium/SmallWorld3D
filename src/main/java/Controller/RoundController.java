package Controller;

import Model.RoundModel;
import Observer.RoundObserver;


public class RoundController {

    private RoundModel model;
    private GameController gameCon;

    RoundController(GameController gameCon){
        model = new RoundModel(8);
        this.gameCon = gameCon;
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
