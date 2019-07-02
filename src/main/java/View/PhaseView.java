package View;

import Controller.PhaseController;
import Observable.PhaseObservable;
import Observer.PhaseObserver;
import Phase.Phase;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Enum.GameViewEnum;
public class PhaseView implements PhaseObserver {

    private Group group;
    private PhaseController phaseCon;

    public Pane pane;
    public Text phaseField;

    public PhaseView(PhaseController phaseCon){
        this.group = GameViewEnum.PHASE.getGroup();
        this.phaseCon = phaseCon;
    }

    public void initialize() {
        group.getChildren().add(pane);
        phaseCon.register(this);
    }

    private void setTextFields(Phase phase){
        phaseField.setText(phase.getName().toUpperCase());
    }

    @Override
    public void update(PhaseObservable po) {
        setTextFields(po.getPhase());
    }
}
