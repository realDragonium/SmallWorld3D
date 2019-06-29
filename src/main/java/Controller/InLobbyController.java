package Controller;

import Firebase.FirebaseGameObserver;
import Model.InLobbyModel;
import Observer.InLobbyObserver;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.Map;

public class InLobbyController implements FirebaseGameObserver { ;
    private InLobbyModel mod = new InLobbyModel();

    public InLobbyController(){

    }

    InLobbyController(String lobbyNaam, int id){
;
        setLobbyNaam(lobbyNaam);
    }

    public InLobbyController(String lobbyNaam){

        setLobbyNaam(lobbyNaam);
    }

    void setLobbyNaam(String lobbyNaam){
        mod.setLobbyNaam(lobbyNaam);
    }

    public void start(){            // start button
        //new GameController(mod.getLobbyNaam(), app.getAccountCon().getPlayerId());  // starten van het spel
    }


    public void exitLobby(){
        new LobbyController();
    }

    public void register(InLobbyObserver ob){
        mod.register(ob);
    }

    public void unregister(InLobbyObserver ob){
        mod.unregister(ob);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        Map<String, Object> map = ds.getData();
        mod.setPlayer( 1, (String)map.get("player1"));
        mod.setPlayer( 2, (String)map.get("player2"));
        mod.setPlayer( 3, (String)map.get("player3"));
        mod.setPlayer( 4, (String)map.get("player4"));
    }



}
