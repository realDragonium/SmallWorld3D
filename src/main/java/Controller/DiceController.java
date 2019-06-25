package Controller;

import Managers.SceneManager;
import Model.DiceModel;
import Observer.DiceObserver;

public class DiceController {

    private DiceModel diceModel = new DiceModel();


    int ClickedDice() {
        int uitkomst = RollDice();
        SceneManager.getInstance().addToScene("diceGroup");
        diceModel.play(uitkomst);
        return diceModel.giveValue(uitkomst);
    }


    DiceController() {
//        SceneManager.getInstance().loadDice(this);
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


