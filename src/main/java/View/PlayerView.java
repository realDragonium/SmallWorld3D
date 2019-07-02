package View;

import Controller.PlayerController;
import Observable.PlayerObservable;
import Observer.PlayerObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Enum.GameViewEnum;

public class PlayerView implements PlayerObserver {

    private int id;
    private Group group;
    private PlayerController playerCon;

    @FXML
    private Pane pane;
    @FXML
    private Text playerId, fiches, punten;


    public PlayerView(int id, PlayerController playerCon) {
        this.group = GameViewEnum.PLAYER.getGroup();
        this.playerCon = playerCon;
        this.id = id;
    }

    public void initialize() {
        playerId.setText("Player"+id);
        playerCon.register(this);
        pane.setLayoutY(100 + 150 * id);
        group.getChildren().add(pane);
    }

    @Override
    public void update(PlayerObservable po) {
        updateFields(po.getRaceFichesAmount(), po.getPoints());
    }

    private void updateFields(int fiches, int punten) {
        this.fiches.setText("" + fiches);
        this.punten.setText("" + punten);
    }
}
