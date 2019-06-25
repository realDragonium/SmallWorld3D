package View;

import Controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class GameView {

    private Group root;
    private GameController gameCon;

    @FXML
    private Group mapGroup, buttonGroup, playerGroup, roundGroup, turnGroup;
    @FXML
    private Pane headPane;


    public GameView(GameController gameCon, Group group){
        this.gameCon = gameCon;
        root = group;
    }

    public void initialize() {
        root.getChildren().add(headPane);
        gameCon.createMap2DView(mapGroup);
        gameCon.createPlayerView(playerGroup);
    }


}
