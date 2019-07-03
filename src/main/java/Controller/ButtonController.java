package Controller;

import Enums.NotificationEnum;
import Objects.SpecialFXMLLoader;
import View.ButtonView;

import java.util.concurrent.Callable;

public class ButtonController {
    private GameController gameCon;

    ButtonController(GameController gameCon){
        this.gameCon = gameCon;
        createButtonView();
    }

    private void createButtonView() {
        new SpecialFXMLLoader().loader("/ButtonView.fxml", (Callable<ButtonView>) () -> new ButtonView(this));
    }

    public void showInfo() {

    }

    public void nextPhase(){
        //gameCon.getFireBase().nextPhaseAction();
        System.out.println(gameCon.rollDice());
        //gameCon.createVote(3, "skip turn?");
    }



    public void createShopItem() {
        gameCon.createRandomShopItem();
    }

    public void previewNotification() {
        gameCon.setMessage(NotificationEnum.DRAGON);
    }

    public void activeListener() {
//        gameCon.getFireBase().activateListener();
    }
}
