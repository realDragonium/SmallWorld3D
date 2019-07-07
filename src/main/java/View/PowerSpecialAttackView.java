package View;

import Controller.PowerSpecialAttackController;
import Enums.GameViewEnum;
import javafx.scene.control.Button;

public class PowerSpecialAttackView {

    public Button button;

    private PowerSpecialAttackController attackCon;

    public PowerSpecialAttackView(PowerSpecialAttackController con){
        this.attackCon = con;
    }

    public void initialize() {
        GameViewEnum.POWERSPATT.getGroup().getChildren().add(button);
    }

    public void specialAttack() {
        attackCon.AttackArea(button.getId());
    }
}
