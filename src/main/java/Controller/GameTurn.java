package Controller;

import Enum.TurnFase;
import Firebase.FirebaseActionObserver;
import Firebase.FirebaseGameObserver;
import Firebase.FirebaseServiceOwn;
import FirebaseActions.FirebaseAction;
import Phase.*;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.List;

public class GameTurn implements FirebaseGameObserver {

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
        fb.register("turn", this);
    }

    public void nextPhase(){
        phase = phase.nextPhase();
        gameCon.changeGameView(phase.changeView());

    }

    @Override
    public void update(DocumentSnapshot ds) {

    }
}
