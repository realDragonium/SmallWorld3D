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
public class ShopView implements ShopObserver {

    private int combinationViews = 0;

    @FXML
    public Pane pane;
    public Button buy1;
    public Button buy2;
    public Button buy3;
    public Button buy4;
    public Button buy5;
    public Button buy6;

    private Button lastActiveButton;
    private ShopController shopCon;
    Group group;

    public ShopView(ShopController shopCon){
        this.shopCon = shopCon;
        this.group = GameViewEnum.SHOP.getGroup();
    }

    @FXML
    public void buyItem(){
        shopCon.buyToFirebase(Integer.parseInt(lastActiveButton.getId()));
    }

    @FXML
    public void setActive(MouseEvent e){
        lastActiveButton = (Button)e.getSource();
    }


    public void initialize() {
        group.getChildren().add(pane);
        shopCon.registerObserver(this);
        shopCon.setShopPosition(pane.getLayoutX(), pane.getLayoutY());
    }


    @Override
    public void update(ShopObservable so) {
        for(int i = 0; i < so.getShopItems().size(); i++) {
            if(so.getItemPosition(i) != null)
            so.getShopItems().get(i).moveToPosition(so.getItemPosition(i));
        }
    }
}
