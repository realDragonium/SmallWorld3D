package Controller;

import Enum.PhaseEnum;
import Firebase.FirebaseGameObserver;
import Model.PhaseModel;
import Observer.PhaseObserver;
import com.google.cloud.firestore.DocumentSnapshot;

public class PhaseController implements FirebaseGameObserver {


    private GameController gameCon;
    private FirebaseGameController fb;
    private PhaseModel model;

    PhaseController(GameController gameCon) {
        this.gameCon = gameCon;
        model = new PhaseModel();
        fb = gameCon.getFireBase();
        fb.register("phase", this);
    }

     void nextPhase(){
        model.getPhase().nextPhase(this);
    }

    public void setPhase(PhaseEnum phase){
        model.setPhase(phase);
    }

    public void changeView(){
        gameCon.changeGameView(model.getPhase().getView());
    }

    public void nextTurn(){
        gameCon.nextTurn();
    }


    public void register(PhaseObserver po){
        model.register(po);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        //Is niet persee nodig maar just in case.
        if(ds.getString("action").equals("nextPhase")){
            nextPhase();
        }
    }


}
