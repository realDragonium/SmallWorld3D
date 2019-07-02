package View;

import Controller.ShopController;
import Observable.ShopObservable;
import Observer.ShopObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import Enum.GameViewEnum;
import javafx.scene.transform.Translate;
import Enum.RaceEnum;

import java.util.HashMap;
import java.util.Map;

public class ShopView implements ShopObserver {

    private int combinationViews = 0;

    @FXML
    public Pane pane;

    private Map<String, Group> groups = new HashMap<>();
    private Button lastActiveButton;
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
//            if(so.getItemPosition(i) != null){
            Group group = RaceEnum.valueOf(so.getShopItems().get(i).getRace()).getGroup();
            groups.get("group" + i).getChildren().clear();
            groups.get("group" + i).getChildren().add(group);
            System.out.println(i);
//            }
//            so.getShopItems().get(i).moveToPosition(so.getItemPosition(i));

        }
    }
}
