package Controller;

import Enums.GameViewEnum;
import Firebase.FirebaseGameObserver;
import Model.DiceModel;
import Objects.SpecialFXMLLoader;
import Observer.DiceObserver;
import View.DiceView;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.Random;
import java.util.concurrent.Callable;

public class DiceController implements FirebaseGameObserver {

    private DiceModel diceModel = new DiceModel();
    private GameController gameCon;

    DiceController(GameController gameController) {
        gameCon = gameController;
        gameCon.getFireBase().register("dice", this);
        createDiceView();
    }

    private void createDiceView() {
        new SpecialFXMLLoader().loader("/Dice/DiceView.fxml", (Callable<DiceView>) () -> new DiceView(this));
    }

    int rollDice() {
        int uitkomst = RollDice();
        diceModel.play(uitkomst);
        return uitkomst;
    }

    private int RollDice() {
        int randInt = new Random().nextInt(6) + 1;
        int waarde = diceModel.giveValue(randInt);
        diceModel.changeSide(waarde);

        return waarde;

    }

    public void registreer(DiceObserver ob) {
        diceModel.register(ob);
    }

    public void hide() {
        gameCon.removeFromGameView(GameViewEnum.DICE);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        gameCon.addToGameView(GameViewEnum.DICE);
        diceModel.play(ds.getDouble("eyes").intValue());
    }


}


