package View;

import Controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.Group;

public class GameView {

    @FXML
    public Group mapGroup;
    @FXML
    public Group buttonGroup;
    @FXML
    public Group playerGroup;
    @FXML
    public Group roundGroup;
    @FXML
    public Group turnGroup;

    private GameController gameCon;

    public GameView(GameController gameCon) {
        this.gameCon = gameCon;
    }

    public void initialize() {

    }


}
