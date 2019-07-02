package View;

import Controller.CombinationController;
import Observable.CombinationObservable;
import Observer.CombinationObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import Enum.GameViewEnum;
import Enum.RaceEnum;

public class CombinationView implements CombinationObserver {

    private CombinationController comboCon;
    private Group group;
    private boolean initialize = true;

    @FXML
    public Pane root;

    public ImageView race, power;

    public CombinationView(CombinationController combiCon){
        group = RaceEnum.valueOf(combiCon.getRace()).getGroup();
        GameViewEnum.COMBINATION.getGroup().getChildren().add(group);
        this.comboCon = combiCon;
//        group = GameViewEnum.COMBINATION.getGroup();
    }

    public void initialize(){
        this.group.getChildren().add(root);
        comboCon.registerObserver(this);
    }

    public void clickedCombination(){
        comboCon.clickedCombination();
    }


    @Override
    public void update(CombinationObservable co) {
        if(co.getPosition() != null) {
            root.setTranslateX(co.getPosition().getX());
            root.setTranslateY(co.getPosition().getY());
        }
        if(initialize && !co.getRaceId().equals("losttribes")) {
            System.out.println(co.getPowerId() + ".png");
            System.out.println(co.getRaceId() + ".png");
            Image powerImage = new Image("/Images/Powers/" + co.getPowerId() + ".png");
            Image raceImage = new Image("/Images/Races/" + co.getRaceId() + ".png");
            power.setImage(powerImage);
            race.setImage(raceImage);
            initialize = false;
        }
    }
}
