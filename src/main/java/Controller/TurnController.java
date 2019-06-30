package Controller;

import Model.TurnModel;
import Observer.TurnObserver;
import Enum.PhaseEnum;
import Turn.*;

import java.util.Stack;

public class TurnController {

    private TurnModel model;
    private GameController gameCon;
    private PhaseController phaseCon;
    private RoundController roundCon;

    TurnController(GameController gameCon){
        model = new TurnModel(gameCon.getPlayers(), gameCon.imPlayer());
        this.gameCon = gameCon;
        this.phaseCon = gameCon.getPhaseCon();
        this.roundCon = gameCon.getRoundCon();
        model.newRound();
        model.currentPlayer = gameCon.getPlayer(0);
    }

    public void register(TurnObserver to){
        model.register(to);
    }

    void newRound(){
        model.newRound();
    }

    void nextTurn(){
        if(model.getTurns().size() == 0) roundCon.nextRound();

        model.getTurns().pop().nextTurn(phaseCon);

        model.currentPlayer = model.players.get(3 - model.getTurns().size());
        model.notifyObservers();

//        if(model.currentPlayerId.equals("player1")){
//            gameCon.getRoundCon().nextRound();
//        }
//        if(gameCon.isGameOver()) return;
//        model.nextTurn();
//        currentPlayer++;
//        if(currentPlayer == 5){
//            currentPlayer = 1;
//        }
//        gameCon.changePlayerTurn("player"+currentPlayer);
    }

    PlayerController getCurrentPlayer() {
        return model.currentPlayer;
    }

}