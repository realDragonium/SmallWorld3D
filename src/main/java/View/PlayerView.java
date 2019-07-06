package View;

import Controller.CombinationController;
import Controller.PlayerController;
import Enums.RaceEnum;
import Observable.PlayerObservable;
import Observer.PlayerObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Enums.GameViewEnum;

public class PlayerView implements PlayerObserver {


    public Group combination;
    private String name;
    private Group group;
    private PlayerController playerCon;

    @FXML
    private Pane pane;
    @FXML
    private Text playerId, punten;
    public ImageView power;
    public ImageView race;


    public PlayerView(String name, PlayerController playerCon) {
        this.group = GameViewEnum.PLAYER.getGroup();
        this.playerCon = playerCon;
        this.name = name;
    }

    public void initialize() {
        group.getChildren().add(pane);
        playerId.setText(name);
        playerCon.register(this);
        pane.setLayoutY(150 + 160 * playerCon.getId());
        race.setOnMouseClicked(e -> playerCon.getCurrentCombi().clickedCombination());
        power.setOnMouseClicked(e -> playerCon.getCurrentCombi().clickedCombination());
    }

    private void setCombinationImages(String raceId, String powerId) {
        Image raceImage = new Image("/Images/Races/" + raceId + ".png");
        Image powerImage = new Image("/Images/Powers/" + powerId + ".png");
        race.setImage(raceImage);
        power.setImage(powerImage);
    }

    @Override
    public void update(PlayerObservable po) {
        updateFields(po.getPoints());
        if(po.hasActiveCombination())
            setCombinationImages(playerCon.getCurrentCombi().getRaceName(), playerCon.getCurrentCombi().getPower());
        else {
            race.setImage(null);
            power.setImage(null);
        }
        playerId.setText(po.getName());
    }

    private void updateFields(int punten) {
        this.punten.setText("" + punten);
    }
}
