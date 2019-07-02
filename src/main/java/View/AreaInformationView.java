package View;

import Controller.AreaInformationController;
import Observable.AreaInformationObservable;
import Observer.AreaInformationObserver;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Enums.GameViewEnum;

public class AreaInformationView implements AreaInformationObserver {
    private AreaInformationController areaInfoCon;
    private Group group;

    public Pane pane;
    public Text areaType, areaDefenceValue, areaOwner, areaSpecial;
    public ImageView area_picture;
    public Group buttonGroup;

    public AreaInformationView(AreaInformationController areaInfoCon) {
        this.areaInfoCon = areaInfoCon;
        this.group = GameViewEnum.AREAINFO.getGroup();
    }

    public void initialize() {
        group.getChildren().add(pane);
        areaInfoCon.registerObserver(this);
    }

    @Override
    public void update(AreaInformationObservable ao) {
        areaOwner.setText("Owner: " + ao.getArea().getOwnerPlayer().getId());
        buttonGroup.getChildren().clear();

        System.out.println(ao.getArea().getAreaInfoButton());
        if(ao.getArea().getAreaInfoButton().getGroup().getChildren().size() > 0)
            System.out.println(ao.getArea().getAreaInfoButton().getGroup().getChildren().get(0));
        else System.out.println("GEEN KINDEREN!!!!!!!!!!");

        for(int i = 0; i < ao.getArea().getAreaInfoButton().getGroup().getChildren().size(); i++){
            Node button = ao.getArea().getAreaInfoButton().getGroup().getChildren().get(i);
            button.setId(ao.getArea().getId());
        }

        buttonGroup.getChildren().add(ao.getArea().getAreaInfoButton().getGroup());
        Image image = new Image("/Images/" + ao.getArea().getAreaType() + "_info.jpg");
        areaDefenceValue.setText("Defence value: " + ao.getArea().getDefenceValue());
        areaType.setText(ao.getArea().getAreaType().toString().toUpperCase());
        area_picture.setImage(image);
        areaSpecial.setText("Special properties: " + ao.getArea().getSpecialProp());
    }

//    public void attackCountry() {
//        areaInfoCon.AttackArea();
//        exitScreen();
//    }

    public void addFiche() {
        areaInfoCon.addFiche();
    }

    public void removeFiche() {
        areaInfoCon.removeFiche();
    }

    public void leaveArea() {
        areaInfoCon.leaveArea();
    }

    public void exitScreen() {
        areaInfoCon.closeAreaInformation();
    }
}
