package Firebase;

import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;

import javax.swing.text.Document;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseLobbyService {

    private Firestore fb;
    private CollectionReference lobbiesRef;
    private DocumentReference currentLobby;

    public FirebaseLobbyService(Firestore fb){
        this.fb = fb;
        lobbiesRef = fb.collection("Lobbies");
    }

    public DocumentReference joinLobby(String id){
        currentLobby  = lobbiesRef.document(id);
        return lobbiesRef.document(id);
    }


    public DocumentReference createLobby(String id){
        currentLobby  = lobbiesRef.document(id);
        return lobbiesRef.document(id);
    }

    public int getHighestlobbyNumber(){
        List<QueryDocumentSnapshot> list = getQuerySnapshot(lobbiesRef).getDocuments();
        return list.size();
    }

    public void actionDocumentListener(final FirebaseLobbyObserver controller) {
        DocumentReference docRef = currentLobby;
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null) {
                    controller.update(snapshot);
                }
            }
        });
    }

    public void pushDocumentUpdate(FirebaseLobbyObserver lobby){
        lobby.update(getDocumentSnapshot());
    }

    public DocumentSnapshot getDocumentSnapshot(){
        try{
            return currentLobby.get().get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void pushAllLobbiesUpdate(FirebaseAllLobbiesObserver lobbies){
        lobbies.update(getQuerySnapshot(lobbiesRef));
    }

    private QuerySnapshot getQuerySnapshot(CollectionReference colRef){
        try {
            return colRef.get().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void placeStartingCombo(int id, int i, Map<String, Object> map) {
        fb.collection("Games").document(""+id).collection("Actions")
                .document("000"+i).set(map);
    }

//    public void leaveLobby(String player) {
//        Map info = getDocumentSnapshot().getData();
//        ((HashMap)info.get("playerNames")).remove(player);
//        ((HashMap)info.get("playerStates")).remove(player);
//        System.out.println("removing from player: " + player);
//        changeLobbyInfo(info);
//        currentLobby = null;
//    }

//        public void changeLobbyInfo(Object info){
//        System.out.println("updating");
//        currentLobby.update((Map)info);
//    }

//    public int createLobby(){
//        int id = getHighestlobbyNumber() + 1;
//        currentLobby = lobbiesRef.document(Integer.toString(id));
//        currentLobby.set(newLobby());
//        return id;
//    }

//    private Object newLobby() {
//        HashMap<String, Object> info = new HashMap<>();
//        info.put("gameSpeed", "normal");
//        info.put("password", "");
//        info.put("inProgress", false);
//        info.put("playerNames", new HashMap<String, String>());
//        info.put("playerStates", new HashMap<String, Boolean>());
//        return info;
//    }
}
