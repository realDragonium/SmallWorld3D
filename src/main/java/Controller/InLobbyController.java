package Controller;

import Enums.ApplicationViewEnum;
import Firebase.FirebaseActionObserver;
import Firebase.FirebaseGameObserver;
import Firebase.FirebaseLobbyObserver;
import Firebase.FirebaseLobbyService;
import Model.InLobbyModel;
import Observer.InLobbyObserver;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InLobbyController implements FirebaseLobbyObserver { ;
    private InLobbyModel model = new InLobbyModel();
    private FirebaseLobbyService fb;
    private ApplicationController appCon;

    public InLobbyController(ApplicationController appCon){
        this.appCon = appCon;
        fb = appCon.getLobbyFireBase();
    }

    public void joinLobby(String player, int id){
        fb.joinLobby(Integer.toString(id));
        fb.actionDocumentListener(this);
        fb.pushDocumentUpdate(this);
        model.setPlayer(model.getPlayerNames().size() + 1, player);
        model.setMyPlayer("player" + (model.getPlayerNames().size()));
        model.setPlayerReady(model.getPlayerStates().size() + 1, false);
        model.setMyName(player);
        model.setLobbyId(id);
        updateLobbyInfo();
        appCon.setActiveView(ApplicationViewEnum.INLOBBY);
    }

    public void createLobby(String player){
        int id = fb.createLobby();
        fb.actionDocumentListener(this);
        model.setLobbyNaam("lobby" + id);
        model.setPlayer(1, player);
        model.setMyPlayer("player1");
        model.setPlayerReady(1, false);
        model.setLobbyId(id);
        model.setMyName(player);
        model.setAsHost();
        updateLobbyInfo();
        appCon.setActiveView(ApplicationViewEnum.INLOBBY);
    }

    public void updateLobbyInfo(){
        HashMap<String, Object> info = new HashMap<>();
        info.put("playerNames", model.getPlayerNames());
        info.put("playerStates", model.getPlayerStates());
        info.put("password", model.getPassword());
        info.put("lobbyName", model.getLobbyNaam());
        info.put("gameSpeed", model.getGameSpeed());
        info.put("inProgress", model.getStart());
        fb.changeLobbyInfo(info);
    }

    public void start(){            // start button
        new GameController(appCon);  // starten van het spel
    }


    public void exitLobby(){
        model.reset();
        fb.leaveLobby(model.getMyPlayer());
        appCon.setActiveView(ApplicationViewEnum.LOBBY);
    }

    public void register(InLobbyObserver ob){
        model.register(ob);
    }

    public void unregister(InLobbyObserver ob){
        model.unregister(ob);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        Map<String, Object> info = ds.getData();
        Map<String, String> players = (Map)info.get("playerNames");
        for(int i = 1; i <= players.size(); i++){
            model.setPlayer(i, players.get("player"+i));
        }

        Map<String, Boolean> states = (Map)info.get("playerStates");
        for(int i = 1; i <= states.size(); i++){
            model.setPlayerReady(i, states.get("player"+i));
        }
        model.setLobbyNaam((String)info.get("lobbyName"));
        model.setPassword((String)info.get("password"));
    }
}
