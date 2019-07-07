package View;

import Controller.AttackController;
import Enums.GameViewEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class AttackView {


    private AttackController attackCon;

    @FXML
    public Button attackButton;

    public AttackView(AttackController attackCon) {
        this.attackCon = attackCon;
    }

    public void initialize(){
        GameViewEnum.ATTACK.getGroup().getChildren().add(attackButton);
    }

    public void attackCountry() {
        attackCon.AttackArea(attackButton.getId());

    }


}
