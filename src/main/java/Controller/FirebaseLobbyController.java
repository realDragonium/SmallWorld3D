package Controller;

import Firebase.FirebaseAllLobbiesObserver;
import Firebase.FirebaseLobbyObserver;
import Firebase.FirebaseLobbyService;
import com.google.cloud.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class FirebaseLobbyController implements  Runnable {

    private ApplicationController appCon;
    private FirebaseLobbyService service;
    private DocumentReference currentLobby;

    FirebaseLobbyController(ApplicationController appCon){
        this.appCon = appCon;
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
        info.put("playerNames", new HashMap<String, String>());
        info.put("playerStates", new HashMap<String, Boolean>());
        return info;
    }

    void leaveLobby(String player) {
        Map info = service.getDocumentSnapshot().getData();
        ((HashMap)info.get("playerNames")).remove(player);
        ((HashMap)info.get("playerStates")).remove(player);
        System.out.println("removing from player: " + player);
        changeLobbyInfo(info);
        currentLobby = null;
    }

    void pushDocumentUpdate(FirebaseLobbyObserver lobby){
        service.pushDocumentUpdate(lobby);
    }

    void actionDocumentListener(FirebaseLobbyObserver controller){
        service.actionDocumentListener(controller);
    }


    public void pushAllLobbiesUpdate(FirebaseAllLobbiesObserver lobbies){
        service.pushAllLobbiesUpdate(lobbies);
    }

    @Override
    public void run() {
        service = appCon.getFirestore().getfbLobby();
    }
}
