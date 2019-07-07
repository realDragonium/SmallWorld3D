package View;

import Controller.RaceSpecialAttackController;
import Enums.GameViewEnum;
import javafx.scene.control.Button;

public class RaceSpecialAttackView {

    public Button button;

    private RaceSpecialAttackController attackCon;

    public RaceSpecialAttackView(RaceSpecialAttackController con){
        this.attackCon = con;
    }

    public void initialize() {
        GameViewEnum.RACESPATT.getGroup().getChildren().add(button);
    }

    public void specialAttack() {
        attackCon.AttackArea(button.getId());
    }
}
