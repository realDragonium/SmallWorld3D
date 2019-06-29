package Controller;

import Enum.TurnFase;
import Model.TurnModel;
import Observer.TurnObserver;

public class TurnController {


    private TurnModel model;
    private GameController gameCon;
    private int currentPlayer;


    TurnController(GameController gameCon){
        model = new TurnModel(4);
        this.gameCon = gameCon;
        decideStartingPlayer();
    }

    private void decideStartingPlayer(){
        currentPlayer = 1;
        gameCon.setCurrentPlayer(currentPlayer);

    }

    public void register(TurnObserver to){
        model.register(to);
    }

    void nextTurn(){
        model.nextTurn();
        gameCon.changePlayerTurn(model.currentPlayerId);
        if(model.currentPlayerId.equals("player1")){
            gameCon.getRoundCon().nextRound();
        }
//        if(gameCon.isGameOver()) return;
//        model.nextTurn();
//        currentPlayer++;
//        if(currentPlayer == 5){
//            currentPlayer = 1;
//        }
//        gameCon.changePlayerTurn("player"+currentPlayer);
    }

    String getCurrentPlayer() {
        return "player" + model.currentTurn;
    }

    void setFase(TurnFase currentPhase) {
        model.setFase(currentPhase);
    }
}