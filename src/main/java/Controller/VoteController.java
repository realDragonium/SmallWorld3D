package Controller;

import Model.VoteModel;
import Objects.SpecialFXMLLoader;
import Observer.VoteObserver;
import View.VoteView;

import java.util.concurrent.Callable;

public class VoteController{
    VoteModel model;


    public VoteController(int neededVotes){
        model = new VoteModel(neededVotes);
        createVoteView();
    }
    public VoteController(int neededVotes, String message){
        model = new VoteModel(neededVotes);
        model.setMessage(message);
        createVoteView();
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
        addVote();
        if(model.getVotesNeeded() - 1 == model.getCurrentVotes()) skipTurn();
    }

    public void voteFireStore(){

        //hier moet een seintje aan fireBase worden gegeven dat er een vote bij moet komen
    }

    private void skipTurn() {
        //hier aanroepen wat ervoor zorgt dat de beurt eindigd
    }

    //deze moet worden aangeroepen door fireBase
    public void update(){
        addVote();
    }
}
