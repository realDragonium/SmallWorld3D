package View;

import Controller.ShopController;
import Enum.GameViewEnum;
import Enum.RaceEnum;
import Observable.ShopObservable;
import Observer.ShopObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class ShopView implements ShopObserver {

    @FXML
    public Pane pane;

    private Map<String, Group> groups = new HashMap<>();
    private ShopController shopCon;
    Group group;

    public ShopView(ShopController shopCon) {
        this.shopCon = shopCon;
        this.group = GameViewEnum.SHOP.getGroup();
    }


    public void initialize() {
        group.getChildren().add(pane);
        shopCon.registerObserver(this);
//        shopCon.setShopPosition(pane.getLayoutX(), pane.getLayoutY());
        for (int i = 0; i < 6; i++) {
            Group group = new Group();
            group.setLayoutX(83);
            group.setLayoutY(i * 107.5 + 94);
            pane.getChildren().add(group);
            groups.put("group" + i, group);

        }
    }


    @Override
    public void update(ShopObservable so) {
        for(int i = 0; i < so.getShopItems().size(); i++) {
            String race = so.getShopItems().get(i).getRace();
            Group group = RaceEnum.valueOf(race).getGroup();
            groups.get("group" + i).getChildren().clear();
            groups.get("group" + i).getChildren().add(group);
        }
    }
}
