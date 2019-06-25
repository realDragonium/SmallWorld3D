package View;

import Controller.VervallenController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * @author : Martijn van der Steen
 * @version : Juni 2019
 */

public class VervallenView {


    public Button vervalButton;
    Group group;
    public Pane root;

    private VervallenController vervalCon;
    private TextField vervaltext;


    /**
     * @param vervalCon is een variable van de VervallenController waar logica staat om het ras van van activiteit te veranderen.
     * @param group wordt in de constructor meegegeven om alle omvattende nodes uit het fxml mee te sturen
     */
    public VervallenView(VervallenController vervalCon, Group group){
        this.vervalCon = vervalCon;
        this.group = group;
    }

    /**
     * De methode initialize zorgt ervoor dat de pane aan de Group wordt toegevoegt.
     */
    public void initialize(){
        group.getChildren().addAll(root);
    }

    /**
     * De methode inVerval() wordt aangeroepen door een (button)onClick action.
     */
    public void inVerval(){vervalCon.inVerval();}

}