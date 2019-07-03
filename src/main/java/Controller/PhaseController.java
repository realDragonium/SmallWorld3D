package Controller;

import Enums.AreaInfoEnum;
import Enums.PhaseEnum;
import Firebase.FirebaseGameObserver;
import Model.PhaseModel;
import Objects.SpecialFXMLLoader;
import Observer.PhaseObserver;
import Phase.Phase;
import View.PhaseView;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.concurrent.Callable;

public class PhaseController implements FirebaseGameObserver {

    private GameController gameCon;
    private FirebaseGameController fb;
    private TurnController turnCon;
    private PhaseModel model;

    PhaseController(GameController gameCon) {
        this.gameCon = gameCon;
        model = new PhaseModel();
        fb = gameCon.getFireBase();
        fb.register("phase", this::update);
        createPhaseView();
    }

    public void setTurnCon(TurnController turnCon){
        this.turnCon = turnCon;
    }

    private void createPhaseView() {
        new SpecialFXMLLoader().loader("/PhaseView.fxml", (Callable<PhaseView>) () -> new PhaseView(this));
    }

    void nextPhase(){
        model.getPhase().nextPhase(this);
    }

    public void setPhase(PhaseEnum phase){
        model.setPhase(phase);

    }

    public Phase getPhase(){
        return model.getPhase();
    }

    public void myTurn(){
        setPhase(PhaseEnum.PREPARING);
        changeView();
    }

    public void notMyTurn(){
        setPhase(PhaseEnum.SPECTATEPREPARING);
    }

    public void changeView(){
        if(turnCon.getCurrentPlayer().getCurrentCombi() == null) return;
        model.getPhase().setViews(turnCon.getCurrentPlayer().getCurrentCombi());
    }

    public void nextTurn(){
       countPoints();
       turnCon.nextTurn();
    }

    public void clearAreaInfoView(){
        turnCon.getCurrentPlayer().getCurrentCombi().cleareAreaInfo();
    }

    public void register(PhaseObserver po){
        model.register(po);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        nextPhase();
    }

    public void countPoints() {
        System.out.println("count them points");
        for(CombinationController combi : turnCon.getCurrentPlayer().getCombinations()){
            combi.countPoints();
        }
    }
}
