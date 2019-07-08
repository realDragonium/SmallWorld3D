package Controller;

import Enums.GameViewEnum;
import Enums.PhaseEnum;
import Firebase.FirebaseGameObserver;
import Model.PhaseModel;
import Objects.SpecialFXMLLoader;
import Observer.PhaseObserver;
import Phase.Phase;
import Special.RedeployPhase.RedeployPhase;
import Special.SpecialAction;
import View.PhaseView;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.concurrent.Callable;

public class PhaseController implements FirebaseGameObserver {

    private GameController gameCon;
    private TurnController turnCon;
    private PhaseModel model;

    PhaseController(GameController gameCon) {
        this.gameCon = gameCon;
        model = new PhaseModel();
        gameCon.getFireBase().register("phase", this);
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
        setTimer(phase.getPhase().getTime(), phase.getPhase().myTurn());
        changeView();
    }

    public Phase getPhase(){
        return model.getPhase();
    }

    public void myTurn(){
        setPhase(PhaseEnum.PREPARING);
        changeView();
    }

    public void startShopTurn() {
        gameCon.getShopCon().buyButtonsOn();
        setPhase(PhaseEnum.PREPARING);
    }

    public void notMyTurn(){
        setPhase(PhaseEnum.SPECTATEPREPARING);
    }

    public void changeView(){
        if(turnCon.getCurrentCombi() == null) return;
        model.getPhase().setViews(turnCon.getCurrentCombi());
        if(turnCon.getCurrentPlayer().getId() == gameCon.getMyPlayerId()){
            gameCon.addToGameView(GameViewEnum.BUTTON);
        }
    }

    public void nextTurn(){
        countPoints();
        turnCon.nextTurn();
        gameCon.removeFromGameView(GameViewEnum.BUTTON);
    }

    public void clearAreaInfoView(){
        if(turnCon.getCurrentCombi() != null)
        turnCon.getCurrentCombi().cleareAreaInfo();
    }

    private void countPoints() {
        if(turnCon.getCurrentCombi() == null) return;
        turnCon.getCurrentCombi().countPoints();
    }

    public void register(PhaseObserver po){
        model.register(po);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        nextPhase();
        gameCon.removeFromGameView(GameViewEnum.VOTE);
    }



    public void setTimer(int time, boolean b) {
        gameCon.setTimer(time, b);
    }

    public void useRedeployingPowers(){
        CombinationController combi = turnCon.getCurrentCombi();
        SpecialAction power = turnCon.getCurrentCombi().getPowerSpecialAction();
        SpecialAction race = turnCon.getCurrentCombi().getRaceSpecialAction();
        if (power instanceof RedeployPhase)
            ((RedeployPhase)power).doAction(gameCon, combi);
        if (race instanceof RedeployPhase)
            ((RedeployPhase) race).doAction(gameCon, combi);
    }


}
