package View;

import Controller.PhaseController;
import Observable.PhaseObservable;
import Observer.PhaseObserver;
import Phase.Phase;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PhaseView implements PhaseObserver {

    private Group group;
    private PhaseController phaseCon;

    public Pane pane;
    public Text phaseField;

    public PhaseView(Group group, PhaseController phaseCon){
        this.group = group;
        this.phaseCon = phaseCon;
    }

    public void initialize() {
        group.getChildren().add(pane);
        phaseCon.register(this);
    }

    private void setTextFields(Phase phase){
        phaseField.setText("Phase: " + phase.getName());
    }

    @Override
    public void update(PhaseObservable po) {
        setTextFields(po.getPhase());
    }
}
