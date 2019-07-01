package Controller;

import Model.TurnModel;
import Observer.TurnObserver;
import javafx.scene.transform.Translate;

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
        PlayerController player = model.currentPlayer;
        Translate cameraPos = new Translate(player.get3dPos().getX(), -700, player.get3dPos().getZ());
        gameCon.getCameraCon().moveToPosition(cameraPos, 5);
        int rotationY = 0;
        if(player.get3dPos().getX() == -600){
            rotationY = 90;
        }
        else if(player.get3dPos().getZ() == 600){
            rotationY = 180;
        }

        else if(player.get3dPos().getX() == 600){
            rotationY = 270;
        }

        gameCon.getCameraCon().rotateToAngle(-60, rotationY , 5);
        model.notifyObservers();
    }

    PlayerController getCurrentPlayer() {
        return model.currentPlayer;
    }

}