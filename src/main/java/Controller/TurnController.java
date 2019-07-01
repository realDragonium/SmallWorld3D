package Controller;

import Firebase.FirebaseGameObserver;
import Model.TurnModel;
import Observer.TurnObserver;
import com.google.cloud.firestore.DocumentSnapshot;

public class TurnController implements FirebaseGameObserver {

    private TurnModel model;
    private GameController gameCon;
    private PhaseController phaseCon;
    private RoundController roundCon;
    private FirebaseGameController fbGame;

    TurnController(GameController gameCon){
        model = new TurnModel(gameCon.getPlayers(), gameCon.imPlayer());
        fbGame = gameCon.getFireBase();
        this.gameCon = gameCon;
        this.phaseCon = gameCon.getPhaseCon();
        this.roundCon = gameCon.getRoundCon();
        model.newRound();
        model.currentPlayer = gameCon.getPlayer(0);
        fbGame.register("attack", this::attackUpdate);
        fbGame.register("decline", this::update);
    }

    public void register(TurnObserver to){
        model.register(to);
    }

    void newRound(){
        model.newRound();
    }

    void nextTurn(){
        if(model.getTurns().size() == 0) roundCon.nextRound();

        model.getTurns().pop().nextTurn(phaseCon);

        model.currentPlayer = model.players.get(3 - model.getTurns().size());
        model.notifyObservers();
    }

    PlayerController getCurrentPlayer() {
        return model.currentPlayer;
    }

    @Override
    public void update(DocumentSnapshot ds) {
        //Decline updates
        getCurrentPlayer().getCurrentCombi().goIntoDecline();
    }

    public void attackUpdate(DocumentSnapshot ds){
        AreaController area = gameCon.getMapCon().getAreaCon(ds.getString("areaId"));
        getCurrentPlayer().getActiveCombination().attackThisArea(area);
    }

}