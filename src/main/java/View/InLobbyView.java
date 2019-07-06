package View;

import Controller.InLobbyController;
import Observable.InLobbyObservable;
import Observer.InLobbyObserver;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class InLobbyView implements InLobbyObserver {

    private InLobbyController con;
    private Group group;
    public Pane pane;


    public InLobbyView(InLobbyController con, Group group) {
        this.group = group;
        this.con = con;
    }

    public void initialize() {
        group.getChildren().add(pane);
        con.register(this);
    }


    // starten van het spel
    public void start(){
        con.start();
    }

//    public void joinen(){
//      //  spelerToevoegen();
//    }

    public void exit(){
        con.exitLobby();
    }

    @Override
    public void update(InLobbyObservable ilo){
//        Player1.setText(ilo.getPlayer1());
//        Player2.setText(ilo.getPlayer2());
//        Player3.setText(ilo.getPlayer3());
//        Player4.setText(ilo.getPlayer4());
    }


    public void exitLobby() {
        con.exitLobby();
    }
}
