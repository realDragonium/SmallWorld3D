package Controller;

import Firebase.FirebaseAllLobbiesObserver;
import Firebase.FirebaseLobbyObserver;
import Firebase.FirebaseLobbyService;
import com.google.cloud.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class FirebaseLobbyController {

    private FirebaseLobbyService service;
    private DocumentReference currentLobby;


    public void setService(FirebaseLobbyService service){
        this.service = service;
    }

    void changeLobbyInfo(Object info){
        currentLobby.update((Map)info);
    }

    void joinLobby(String id){
        currentLobby = service.joinLobby(id);
    }

    private int getHighestlobbyNumber(){
        return service.getHighestlobbyNumber();
    }

    int createLobby(){
        int id = getHighestlobbyNumber() + 1;
        currentLobby = service.createLobby(Integer.toString(id));
        currentLobby.set(newLobby());
        return id;
    }

    private Object newLobby() {
        HashMap<String, Object> info = new HashMap<>();
        info.put("gameSpeed", "normal");
        info.put("password", "");
        info.put("inProgress", false);
        HashMap<String, String> names = new HashMap<>();
        names.put("player1", "");
        names.put("player2", "");
        names.put("player3", "");
        names.put("player4", "");
        info.put("playerNames", names);
        HashMap<String, Boolean> states = new HashMap<>();
        states.put("player1", false);
        states.put("player2", false);
        states.put("player3", false);
        states.put("player4", false);
        info.put("playerStates", states);
        return info;
    }

    void leaveLobby(String player) {
        Map info = service.getDocumentSnapshot().getData();
        ((HashMap)info.get("playerNames")).put(player, "");
        ((HashMap)info.get("playerStates")).put(player, false);
        changeLobbyInfo(info);
        currentLobby = null;
    }

    void pushDocumentUpdate(FirebaseLobbyObserver lobby){
        service.pushDocumentUpdate(lobby);
    }

    void actionDocumentListener(FirebaseLobbyObserver controller){
        service.actionDocumentListener(controller);
    }


    void pushAllLobbiesUpdate(FirebaseAllLobbiesObserver lobbies){
        service.pushAllLobbiesUpdate(lobbies);
    }

}
