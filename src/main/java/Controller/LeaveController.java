package Controller;

import Enums.GameViewEnum;
import Objects.SpecialFXMLLoader;
import View.LeaveView;

import java.util.concurrent.Callable;

public class LeaveController {
    private GameController gameCon;


    public LeaveController(GameController gameCon){
        this.gameCon = gameCon;
        createLeaveView();
    }

    private void createLeaveView() {
        new SpecialFXMLLoader().loader("/LeaveView.fxml", (Callable<LeaveView>) () -> new LeaveView(this));
    }

    public void leaveArea(String areaId){
        gameCon.removeFromGameView(GameViewEnum.AREAINFO);
        gameCon.getFireBase().leavesFicheAction(areaId);
    }

}
