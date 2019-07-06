package Controller;

import Enums.GameViewEnum;
import Firebase.FirebaseActionObserver;
import Firebase.FirebaseGameObserver;
import Model.VoteModel;
import Objects.SpecialFXMLLoader;
import Observer.VoteObserver;
import View.VoteView;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class VoteController implements FirebaseActionObserver {
    private VoteModel model;
    private FirebaseGameController fbGame;
    private GameController gameCon;

    public VoteController(int neededVotes){
        model = new VoteModel(neededVotes);
        createVoteView();
    }

    public VoteController(int neededVotes, String message, GameController gameCon){
        model = new VoteModel(neededVotes);
        model.setMessage(message);
        createVoteView();
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
        fbGame.voteListener(this::update);
    }

    private void createVoteView() {
        new SpecialFXMLLoader().loader("/VoteView.fxml", (Callable<VoteView>)() -> new VoteView(this));
    }

    public void addVote(){
        model.addVote();
    }

    public void registerObserver(VoteObserver obs){
        model.register(obs);
    }

    public void doVote(){
        model.disableVote();
        voteFireStore();
        //addVote();
        if(model.getVotesNeeded() - 1 == model.getCurrentVotes()) skipTurn();
    }

    public void voteFireStore(){
        fbGame.placeVote();
        //hier moet een seintje aan fireBase worden gegeven dat er een vote bij moet komen
    }

    private void skipTurn() {
        //hier aanroepen wat ervoor zorgt dat de beurt eindigd
        fbGame.nextTurnAction();
        TimerTask delete = new TimerTask() {
            @Override
            public void run() {
                fbGame.deleteVotes();
            }
        };
        new Timer().schedule(delete, 500);
    }

    //deze moet worden aangeroepen door fireBase
    @Override
    public void update(QuerySnapshot qs) {
        addVote();
    }
}
