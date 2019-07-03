package Model;

import Observable.VoteObservable;
import Observer.VoteObserver;

public class VoteModel implements VoteObservable {

    private VoteObserver observer;
    private int neededVotes;
    private int currentVotes = 0;
    private boolean voted = false;
    private String message = "none";

    public VoteModel(int neededVotes){
        this.neededVotes = neededVotes;
    }

    public void setMessage(String string){
        message = string;
    }

    @Override
    public void register(VoteObserver ob) {
        observer = ob;
        notifyAllObs();
    }

    @Override
    public void notifyAllObs() {
        observer.update(this);
    }

    @Override
    public int getCurrentVotes() {
        return currentVotes;
    }

    @Override
    public int getVotesNeeded() {
        return neededVotes;
    }

    @Override
    public boolean hasVoted() {
        return voted;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void addVote() {
        currentVotes++;
        notifyAllObs();
    }

    public void disableVote() {
        voted = true;
        notifyAllObs();
    }
}
