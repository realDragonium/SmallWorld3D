package Controller;

import Enums.ApplicationViewEnum;
import Firebase.FirebaseLobbyObserver;
import Model.InLobbyModel;
import Observer.InLobbyObserver;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class InLobbyController implements FirebaseLobbyObserver { ;
    private InLobbyModel model = new InLobbyModel();
    private FirebaseLobbyController fb;
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
        int lobbyId = fb.createLobby();
        fb.actionDocumentListener(this);
        model.setPlayer(1, player);
        model.setLobbyNaam("lobby" + lobbyId);
        model.setPlayerReady(1, false);
        model.setMyPlayer("player1");
        model.setMyName(player);
        model.setLobbyId(lobbyId);
        updateLobbyInfo();
        appCon.setActiveView(ApplicationViewEnum.INLOBBY);
    }

    public HashMap getGameInfo(){
        HashMap<String, Object> info = new HashMap<>();
        info.put("playerNames", model.getPlayerNames());
        info.put("playerStates", model.getPlayerStates());
        info.put("lobbyId", Integer.toString(model.getLobbyId()));
        info.put("password", model.getPassword());
        info.put("lobbyName", model.getLobbyNaam());
        info.put("gameSpeed", model.getGameSpeed());
        info.put("inProgress", model.getStart());
        return info;
    }

    public void updateLobbyInfo(){

        fb.changeLobbyInfo(getGameInfo());
    }

    public void start(){            // start button


        //moet eigenlijk update geven aan iedereen en niet hier starten
        appCon.startGame(getGameInfo()); // starten van het spel
        model.startGame(true);
        updateLobbyInfo();
    }


    public void exitLobby(){
        model.reset();
        fb.leaveLobby(model.getMyPlayer());
        appCon.getLobbyCon().setAsActive();
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
        model.startGame((Boolean)info.get("inProgress"));
    }
}
