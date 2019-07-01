package View;

import Controller.AreaInformationController;
import Observable.AreaInformationObservable;
import Observer.AreaInformationObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AreaInformationView implements AreaInformationObserver {
    private AreaInformationController areaInfoCon;
    private Group group;

    public Pane pane;
    public Text areaType, areaDefenceValue, areaOwner, areaSpecial;
    public ImageView area_picture;

    public AreaInformationView(Group group, AreaInformationController areaInfoCon){
        this.areaInfoCon = areaInfoCon;
        this.group = group;
    }

    public void initialize(){
        group.getChildren().add(pane);
        areaInfoCon.registerObserver(this);
    }

    @Override
    public void update(AreaInformationObservable ao) {
        areaOwner.setText("Owner: " + ao.getArea().getOwnerPlayer().getId());

        Image image = new Image("/Images/" + ao.getArea().getAreaType() + "_info.jpg");
        areaDefenceValue.setText("Defence value: " + ao.getArea().getDefenceValue());
        areaType.setText(ao.getArea().getAreaType().toString().toUpperCase());
        area_picture.setImage(image);
        areaSpecial.setText("Special properties: " + ao.getArea().getSpecialProp());
    }

    public void attackCountry() {
        areaInfoCon.AttackArea();
        exitScreen();
    }

    public void addFiche() {
    }

    public void removeFiche() {
    }

    public void leaveArea() {
    }

    public void exitScreen() {
        areaInfoCon.closeAreaInformation();
    }
}
