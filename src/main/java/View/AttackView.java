package View;

import Controller.AttackController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


    public class AttackView {

        private Group root;
        private AttackController attackCon;

        @FXML
        public Pane pane;
        public Button attackButton;

        public AttackView(Group group, AttackController attackCon){
            root = group;
            this.attackCon = attackCon;
        }

        public void initialize() {
            root.getChildren().add(pane);
        }

        @FXML
        public void attackCountry(){
            attackCon.attackCountry();
        }


}
