package View;

import Controller.PlayerController;
import Observable.PlayerObservable;
import Observer.PlayerObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PlayerView implements PlayerObserver {

    private String id;
    private Group group;
    private PlayerController playerCon;

    @FXML
    private Pane pane;
    @FXML
    private Text playerId, fiches, punten;


    public PlayerView(String id, Group group, PlayerController playerCon){
        this.group = group;
        this.playerCon = playerCon;
        this.id = id;
    }

    public void initialize() {
        if(Integer.parseInt(id.split("yer")[1]) != 0){
            playerId.setText(id);
            playerCon.register(this);
            pane.setLayoutY((100 + 150*Integer.parseInt(id.split("yer")[1])));
            group.getChildren().add(pane);
        }

    }

    @Override
    public void update(PlayerObservable po) {
        updateFields(po.getRaceFichesAmount(), po.getPunten());
    }

    private void updateFields(int fiches, int punten){
        this.fiches.setText("" +fiches);
        this.punten.setText("" + punten);
    }
}
