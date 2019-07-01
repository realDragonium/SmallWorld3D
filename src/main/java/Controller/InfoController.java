package Controller;

import Model.InfoModel;
import Objects.FXMLLOADER;
import Observer.infoObserver;
import Enum.GameViewEnum;
import View.InfoView;
import javafx.scene.Group;

import java.util.concurrent.Callable;

public class InfoController {

    private InfoModel infoModel = new InfoModel();
    private GameController gameCon;

    InfoController(GameController gameController) {
        gameCon = gameController;
        createInfoView();
    }


    private void createInfoView() {
        new FXMLLOADER().loader("/InfoScreen/InfoView.fxml", (Callable<InfoView>)() -> new InfoView(this));
    }

    public void exit(){
        gameCon.removeFromGameView(GameViewEnum.INFO);
    }

    public void register(infoObserver ob) {
        infoModel.register(ob);
    }

    public void setText(String menuItem) {

        switch (menuItem) {
            case "Humans":
                infoModel.showInfo(infoModel.Humans);
                break;
            case "Wizards":
                infoModel.showInfo(infoModel.Wizards);
                break;
            case "Dwarves":
                infoModel.showInfo(infoModel.Dwarves);
                break;
            case "Giants":
                infoModel.showInfo(infoModel.Giants);
                break;
            case "Tritons":
                infoModel.showInfo(infoModel.Tritons);
                break;
            case "Ratmen":
                infoModel.showInfo(infoModel.Ratmen);
                break;
            case "Pick Race":
                infoModel.showInfo(infoModel.pickRace);
                break;
            case "First Conquest":
                infoModel.showInfo(infoModel.firstConquest);
                break;
            case "Conquering a Region":
                infoModel.showInfo(infoModel.conqueringRegion);
                break;
            case "Enemy Losses & Withdrawals":
                infoModel.showInfo(infoModel.enemyLosses);
                break;
            case "Following Conquests":
                infoModel.showInfo(infoModel.followingConquests);
                break;
            case "Final Conquest Attempt/ReinforcementDie Roll":
                infoModel.showInfo(infoModel.finalConquest);
                break;
            case "Troop Redeployment":
                infoModel.showInfo(infoModel.troopRedeployment);
                break;
            case "Victory Coins":
                infoModel.showInfo(infoModel.victoryCoins);
                break;
            case "Decline":
                infoModel.showInfo(infoModel.decline);
                break;
            case "EndGame":
                infoModel.showInfo(infoModel.endGame);
                break;
            case "Ready Troops":
                infoModel.showInfo(infoModel.readyTroops);
                break;
            case "Conquer":
                infoModel.showInfo(infoModel.conquer);
                break;
            case "Abandoning a Region":
                infoModel.showInfo(infoModel.abandoningRegion);
                break;

        }
    }
}
