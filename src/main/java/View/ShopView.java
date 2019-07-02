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

    private Button lastActiveButton;
    private ShopController shopCon;
    private boolean showing;
    Group group;

    public ShopView(ShopController shopCon){
        this.shopCon = shopCon;
        this.group = GameViewEnum.SHOP.getGroup();
    }

    public void buttonClicked(){
        if(showing){
            hideShop();
            showing = false;
        }
        else{
            showShop();
            showing = true;
        }
    }

    public void hideShop(){
        shopCon.hideShop();
    }

    public void showShop(){
        shopCon.showShop();
    }

    public void initialize() {
        System.out.println("maakt de shop aan");
        group.getChildren().add(pane);
        shopCon.registerObserver(this);
        shopCon.setShopPosition(pane.getLayoutX(), pane.getLayoutY());
    }


    @Override
    public void update(ShopObservable so) {
        System.out.println(so.getShopPosition());
        for(int i = 0; i < so.getShopItems().size(); i++) {
            if(so.getItemPosition(i) != null)
            so.getShopItems().get(i).moveToPosition(so.getItemPosition(i));
        }
        if(so.getShopPosition() != null) {
            pane.setLayoutX(so.getShopPosition().getX());
            pane.setLayoutY(so.getShopPosition().getY());
        }
    }
}
