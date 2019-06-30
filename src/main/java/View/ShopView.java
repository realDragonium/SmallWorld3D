package View;

import Controller.ShopController;
import Observable.ShopObservable;
import Observer.ShopObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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

    public ShopView(Group group, ShopController shopCon){
        this.shopCon = shopCon;
        this.group = group;
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
//        shopCon.makeItems();

    }

    public void createCombinationView(int index){
        shopCon.createCombinationView(index, group);
    }

    public void showCombination(){

    }

    @Override
    public void update(ShopObservable so) {
        //Stest");ystem.out.println("
        if(so.getShopItems().size() < combinationViews){
            combinationViews = so.getShopItems().size();

        }

        if(so.getShopItems().size() > combinationViews){
            combinationViews++;
            createCombinationView(combinationViews - 1);
            System.out.println("creating shop view for index: " + combinationViews);
        }

        for(int i = 0; i < so.getShopItems().size(); i++) {
            if(so.getItemPosition(i) != null)
            so.getShopItems().get(i).moveToPosition(so.getItemPosition(i));
        }
    }
}
