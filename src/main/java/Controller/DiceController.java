package Controller;

import Model.DiceModel;
import Objects.FXMLLOADER;
import Observer.DiceObserver;
import View.DiceView;
import javafx.scene.Group;

import java.util.concurrent.Callable;

public class DiceController {

    private DiceModel diceModel = new DiceModel();
    private GameController gameCon;

    DiceController(GameController gameController) {
        gameCon = gameController;
        createDiceView();
    }

    private void createDiceView() {
        new FXMLLOADER().loader("/Dice/DiceView.fxml", (Callable<DiceView>) () -> new DiceView(this));
    }

    int ClickedDice() {
        int uitkomst = RollDice();
        diceModel.play(uitkomst);
        return diceModel.giveValue(uitkomst);
    }

    private int RollDice() {
        int uitkomst = (int) (Math.floor(Math.random() * 6));
        diceModel.changeSide(uitkomst);
        //uitkomst += 1;
        diceModel.giveValue(uitkomst);

        return uitkomst;

    }

    public void registreer(DiceObserver ob) {
        diceModel.register(ob);
    }
}


