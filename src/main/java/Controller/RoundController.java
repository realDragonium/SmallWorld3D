package Controller;

import Managers.SceneManager;
import Model.RoundModel;
import Observer.RoundObserver;

/**
 * @author : Martijn van der Steen
 * @version : Juni 2019
 */

public class RoundController {



    private RoundModel model;
    private GameController gameCon;

    RoundController(GameController gameCon){
        model = new RoundModel(8);
        this.gameCon = gameCon;
//        SceneManager.getInstance().loadRound(this);
    }

    void nextRound() {
        if(model.currentRound >= model.getMaxRounds()) {
            gameCon.endGame();
            return;
        }
        model.nextRound();
    }

    public int getCurrentRound(){
        return model.currentRound;
    }

    public void register(RoundObserver ro ){
        model.register(ro);
    }
}
