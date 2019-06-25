package View;

import Controller.InfoController;
import Managers.SceneManager;
import Observable.infoObservable;
import Observer.infoObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextArea;

import java.awt.*;


public class InfoView implements infoObserver {
    private Group infoGroup;
    private InfoController infoCon;
    @FXML
    public Group pane;

    @FXML
    public TextArea infoText;

    @FXML
    public MenuItem button;

    public InfoView(Group groep, InfoController infoController) {
        infoGroup = groep;
        infoCon = infoController;
        infoCon.register(this);
    }

    @FXML
    public void showHumans() {
        infoCon.setText("Humans");
    }

    @FXML
    public void showWizards() {
        infoCon.setText("Wizards");
    }

    @FXML
    public void showDwarves() {
        infoCon.setText("Dwarves");
    }

    @FXML
    public void showGiants() {
        infoCon.setText("Giants");
    }

    @FXML
    public void showTritons() {
        infoCon.setText("Tritons");
    }

    @FXML
    public void showRatmen() {
        infoCon.setText("Ratmen");
    }

    @FXML
    public void showPickRace() {
        infoCon.setText("Pick Race");
    }

    @FXML
    public void showFirstConquest() {
        infoCon.setText("First Conquest");
    }

    @FXML
    public void showConqueringRegion() {
        infoCon.setText("Conquering a Region");
    }

    @FXML
    public void showEnemyLosses() {
        infoCon.setText("Enemy Losses & Withdrawals");
    }

    @FXML
    public void showFollowingConquests() {
        infoCon.setText("Following Conquests");
    }

    @FXML
    public void showFinalConquest() {
        infoCon.setText("Final Conquest Attempt/ReinforcementDie Roll");
    }

    @FXML
    public void showTroopRedeployment() {
        infoCon.setText("Troop Redeployment");
    }

    @FXML
    public void showVictoryCoins() {
        infoCon.setText("Victory Coins");
    }

    @FXML
    public void showDecline() {
        infoCon.setText("Decline");
    }

    @FXML
    public void showEndGame() {
        infoCon.setText("EndGame");
    }

    @FXML
    public void showReadyTroops() {
        infoCon.setText("Ready Troops");
    }

    @FXML
    public void showConquer() {
        infoCon.setText("Conquer");
    }

    @FXML
    public void showAbandoningRegion() {
        infoCon.setText("Abandoning a Region");
    }

    @Override
    public void update(infoObservable ob) {
        infoText.setWrapText(true);
        infoText.setText(ob.currentText());

    }


    public void initialize() {
        infoGroup.getChildren().add(pane);
    }

    public void exitInfoScreen() {
        SceneManager.getInstance().removeFromStandardScene("infoGroup");
    }
}
