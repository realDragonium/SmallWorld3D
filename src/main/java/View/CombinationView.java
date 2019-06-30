package View;

import Controller.CombinationController;
import Controller.FicheController;
import Observable.CombinationObservable;
import Observer.CombinationObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CombinationView implements CombinationObserver {

    private CombinationController comboCon;
    private Group group;

    @FXML
    public Pane root;
    public Text Race;
    public Text Power;

    public CombinationView(Group group, CombinationController combiCon){
        this.group = group;
        this.comboCon = combiCon;
    }

    public void initialize(){
        this.group.getChildren().add(root);
        comboCon.registerObserver(this);
    }


    @Override
    public void update(CombinationObservable co) {
//        System.out.println(comboCon.getRace());
//        System.out.println(co.getPosition());
        if(co.getPosition() != null) {
            root.setTranslateX(co.getPosition().getX());
            root.setTranslateY(co.getPosition().getY());
        }
        Race.setText(co.getRaceId());
        Power.setText(co.getPowerId());


    }
}
