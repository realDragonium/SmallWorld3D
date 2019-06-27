package View;

import Controller.AreaInformationController;
import Observable.AreaInformationObservable;
import Observer.AreaInformationObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AreaInformationView implements AreaInformationObserver {
    private AreaInformationController areaInfoCon;
    private Group group;

    public Pane pane;
    @FXML
    public Text areaType, areaDefenceValue, areaOwner;

    public AreaInformationView(Group group, AreaInformationController areaInfoCon){
        this.areaInfoCon = areaInfoCon;
        this.group = group;
        areaInfoCon.registerObserver(this);
    }

    public void initialize(){
        group.getChildren().add(pane);
    }

    @Override
    public void update(AreaInformationObservable ao) {
        group.setVisible(ao.isActive());
        if(ao.getArea().getOwnerPlayer() != null){
            areaOwner.setText("Owner: " + ao.getArea().getOwnerPlayer().getId());
        }
        else{
           areaOwner.setText("Owner: none");
        }

        areaDefenceValue.setText("Defence value: " + ao.getArea().getDefenceValue());
        areaType.setText("Type: " + ao.getArea().getAreaType());

    }

    public void attackCountry() {
        areaInfoCon.AttackArea();
        areaInfoCon.closeAreaInformation();
    }

    public void addFiche() {
    }

    public void removeFiche() {
    }

    public void leaveArea() {
    }
}
