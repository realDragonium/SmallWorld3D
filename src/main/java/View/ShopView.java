package View;

import Controller.ShopController;
import Observable.ShopObservable;
import Observer.ShopObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *  Gemaakt door Yoran op 17-6-2019.
 *  ShopView class weergeeft a.h.v. een FXML tag de buttons, waardoor de speler een ras kan kopen.
 *
 */


public class ShopView implements ShopObserver {

    @FXML
    public Pane pane;
    public Button buy1;
    public Button buy2;
    public Button buy3;
    public Button buy4;
    public Button buy5;
    public Button buy6;

    public Group item1;
    public Group item2;
    public Group item3;
    public Group item4;
    public Group item5;
    public Group item6;


    private Button lastActiveButton;
    private ShopController shopCon;
    Group group;

    public ShopView(Group group, ShopController shopCon){
        this.shopCon = shopCon;
        this.group = group;
        shopCon.registerObserver(this);
    }

    @FXML
    public void buyItem(){
        shopCon.buyingItem(Integer.parseInt(lastActiveButton.getId()));
    }

    @FXML
    public void setActive(MouseEvent e){
        lastActiveButton = (Button)e.getSource();
    }


    public void initialize() {
        group.getChildren().add(pane);
    }


    public void showCombination(){

    }

    @Override
    public void update(ShopObservable so) {
        for(int i = 0; i < 6; i++) {
            ((Text) ((Group)pane.getChildren().get(i+1)).getChildren().get(0)).setText(so.getPlayer(i));
            ((Text) ((Group)pane.getChildren().get(i+1)).getChildren().get(1)).setText(so.getPower(i));
        }
    }
}
