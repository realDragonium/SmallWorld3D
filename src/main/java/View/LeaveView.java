package View;

import Controller.LeaveController;
import Enums.GameViewEnum;
import javafx.scene.control.Button;


public class LeaveView {
    private LeaveController leaveCon;

    public Button leaveButton;

    public LeaveView(LeaveController leaveCon){
        this.leaveCon = leaveCon;
    }

    public void initialize() {
        GameViewEnum.LEAVE.getGroup().getChildren().add(leaveButton);
    }

    public void leaveArea(){
        leaveCon.leaveArea(leaveButton.getId());
    }

}
