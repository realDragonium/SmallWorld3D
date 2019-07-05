package View;

import Controller.CombinationInfoController;
import Enums.GameViewEnum;
import Observable.CombinationInfoObservable;
import Observer.CombinationInfoObserver;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CombinationInfoView implements CombinationInfoObserver {

    private CombinationInfoController combiInfoCon;
    private Group group;

    public Pane pane;
    public ImageView race;
    public ImageView power;
    public Button buyButton;

    public CombinationInfoView(CombinationInfoController combiInfoCon) {
        this.combiInfoCon = combiInfoCon;
    }

    public void initialize() {
        GameViewEnum.COMBIINFO.getGroup().getChildren().add(pane);
        pane.getChildren().remove(buyButton);
        combiInfoCon.registerObserver(this);
    }

    @Override
    public void update(CombinationInfoObservable co) {
        race.setImage(new Image("/Images/Races/" + co.getRace() + "Info.png"));
        power.setImage(new Image("/Images/Powers/" + co.getPower() + "Info.png"));
        if(co.inShop()) pane.getChildren().add(buyButton);
    }

    public void exitScreen() {
        combiInfoCon.hideInfoScreen();
    }

    public void buyItem() {
        combiInfoCon.buyThisItem();
    }
}
