package Controller;

import Enum.TurnFase;
import Firebase.FirebaseControllerObserver;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;

class GameTurn implements FirebaseControllerObserver {

    GameController gameCon;
    private FirebaseServiceOwn fb = SceneManager.getInstance().getApp().getFirebaseService();

    void endTurn() {
        gameCon.getGameTimer().endPhase();
    }

    private TurnFase currentPhase;
    private PlayerController currentPlayer;

    GameTurn(GameController gameCon, PlayerController player) {
        fb.timerListen(this);
        this.gameCon = gameCon;
        currentPlayer = player;
        currentPhase = TurnFase.none;

        SceneManager.getInstance().switchToSpectatingView();
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
                SceneManager.getInstance().switchToSpectatingView();
                currentPhase = TurnFase.none;
                gameCon.nextTurn();
                break;
        }
    }

    private void startPreperationPhase() {
        currentPhase = TurnFase.preparing;
        gameCon.getTurnCon().setFase(currentPhase);

        if (currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            SceneManager.getInstance().switchToPreperationPhase();

            if (currentPlayer.hasActiveCombination()) {
                currentPlayer.returnFiches();
                currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);

                SceneManager.getInstance().addToScene("vervalGroup");
            } else {
                SceneManager.getInstance().addToScene("shopGroup");
            }
        }

    }

    private void startAttackPhase() {
        currentPhase = TurnFase.conquering;
        gameCon.getTurnCon().setFase(currentPhase);
        if (currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            if (currentPlayer.hasActiveCombination()) {
                SceneManager.getInstance().switchToAttackPhase();
            } else {
                endTurn();
            }
        }
    }

    private void startEndingPhase() {
        currentPhase = TurnFase.redeploying;
        gameCon.getTurnCon().setFase(currentPhase);
        if (currentPlayer.getId().equals(gameCon.getMyPlayerId())) {
            SceneManager.getInstance().switchToEndingPhase();
            currentPlayer.addRoundPoints();
            if (currentPlayer.hasActiveCombination()) {
                currentPlayer.getActiveCombination().checkForSpecialActions(currentPhase);
            }
        }
    }

    @Override
    public void update(DocumentSnapshot ds) {
        Platform.runLater(() -> {
            endPhase();
            gameCon.getTimer().setTime(gameCon.getGameTimer().maxTime);
            gameCon.getGameTimer().resetTimer(ds.getBoolean("endPhase"));
        });
    }

    void newTurn(PlayerController currentPlayer) {
        this.currentPlayer = currentPlayer;
        endPhase();
    }
}
