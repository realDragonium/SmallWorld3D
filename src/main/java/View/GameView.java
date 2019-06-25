package View;

import Controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class GameView {

    private Group root;
    private GameController gameCon;

    @FXML
    private Group mapGroup, buttonGroup, playerGroup, roundGroup, turnGroup, shopGroup, timerGroup, vervalGroup, diceGroup, redeployingGroup, infoGroup, attackGroup;
    @FXML
    private Pane headPane;


    public GameView(GameController gameCon, Group group){
        this.gameCon = gameCon;
        root = group;
    }

    public void initialize() {
        root.getChildren().add(headPane);
        gameCon.createMap2DView(mapGroup);
        gameCon.createPlayerView(playerGroup, "player1");
        gameCon.createPlayerView(playerGroup, "player2");
        gameCon.createPlayerView(playerGroup, "player3");
        gameCon.createPlayerView(playerGroup, "player4");
        gameCon.createRoundView(roundGroup);
        gameCon.createTurnView(turnGroup);
        gameCon.createButtonView(buttonGroup);
        gameCon.createShopView(shopGroup);
        gameCon.createTimerView(timerGroup);
        gameCon.createVervalView(vervalGroup);
        gameCon.createDiceView(diceGroup);
        gameCon.createRedeployView(redeployingGroup);
//        gameCon.createInfoView(infoGroup);
        gameCon.createAttackView(attackGroup);
    }


}
