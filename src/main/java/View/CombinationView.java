package View;

import Controller.CombinationController;
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

    public CombinationView(Group group, CombinationController comboCon){
        this.comboCon = comboCon;
        this.group = group;
    }

    public void initialize(){
        this.group.getChildren().add(root);
        comboCon.registerObserver(this);
    }


    @Override
    public void update(CombinationObservable co) {
        Race.setText(co.getRaceId());
        Power.setText(co.getPowerId());
    }
}
