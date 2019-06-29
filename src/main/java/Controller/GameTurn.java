package Controller;

import Enum.TurnFase;
import Firebase.FirebaseActionObserver;
import Firebase.FirebaseServiceOwn;
import FirebaseActions.FirebaseAction;
import Phase.*;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.List;

public class GameTurn implements FirebaseActionObserver {

    private Phase phase;
    GameController gameCon;
    private FirebaseGameController fb;

    void endTurn() {
        gameCon.getGameTimer().endPhase();
    }

    private TurnFase currentPhase;
    private PlayerController currentPlayer;

    GameTurn(GameController gameCon, PlayerController player) {
        this.gameCon = gameCon;
        currentPlayer = player;
        phase = new PhaseNone();

    }

    GameTurn(GameController gameCon){
        this.gameCon = gameCon;
        phase = new PhaseNone();
        fb = gameCon.getFireBase();
//        fb.turnActionListener(this);
    }

    public void nextPhase(){
        phase = phase.nextPhase();
        gameCon.changeGameView(phase.changeView());

    }

    private void endPhase() {
        switch (currentPhase) {
            case none:
                startPreperationPhase();
                break;
            case preparing:
                startAttackPhase();
                break;
            case conquering:
                startEndingPhase();
                break;
            case redeploying:
//                SceneManager.getInstance().switchToSpectatingView();
                currentPhase = TurnFase.none;
                gameCon.nextTurn();
                break;
        }
    }

    private void startPreperationPhase() {
        currentPhase = TurnFase.preparing;
        gameCon.getTurnCon().setFase(currentPhase);

        if (currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
//            SceneManager.getInstance().switchToPreperationPhase();

            if (currentPlayer.hasActiveCombination()) {
                currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);

//                SceneManager.getInstance().addToScene("vervalGroup");
            } else {
//                SceneManager.getInstance().addToScene("shopGroup");
            }
        }

    }

    private void startAttackPhase() {
        currentPhase = TurnFase.conquering;
        gameCon.getTurnCon().setFase(currentPhase);
        if (currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            if (currentPlayer.hasActiveCombination()) {
//                SceneManager.getInstance().switchToAttackPhase();
            } else {
                endTurn();
            }
        }
    }

    private void startEndingPhase() {
        currentPhase = TurnFase.redeploying;
        gameCon.getTurnCon().setFase(currentPhase);
        if (currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
//            SceneManager.getInstance().switchToEndingPhase();
            currentPlayer.addRoundPoints();
            if (currentPlayer.hasActiveCombination()) {
                currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
            }
        }
    }


    void newTurn(PlayerController currentPlayer) {
        this.currentPlayer = currentPlayer;
        endPhase();
    }

    @Override
    public void update(QuerySnapshot qs) {
        List<DocumentChange> testList = qs.getDocumentChanges();
//        System.out.println(testList.get(0).getDocument().getId());
        FirebaseAction action = testList.get(0).getDocument().toObject(FirebaseAction.class);
        action.doAction(this);

//        List<QueryDocumentSnapshot> docs = qs.getDocuments();
//        QueryDocumentSnapshot qDoc = docs.get(docs.size()-1);
//        System.out.println(qDoc.getId());
//        FirebaseAction action = qDoc.toObject(FirebaseAction.class);
//        action.doAction(this);
    }
}
