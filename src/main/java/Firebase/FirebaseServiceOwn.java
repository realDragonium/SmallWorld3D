package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;
import javafx.application.Platform;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class FirebaseServiceOwn {
    private Firestore firestore;
    private CollectionReference colRef;
    private DocumentReference gameRef;

    public FirebaseServiceOwn() {
        Database db = new Database();
        this.firestore = db.getFirestoreDatabase();
        this.colRef = this.firestore.collection("Games");
    }

    public void setGame(String lobbyName){
        gameRef = colRef.document(lobbyName);
    }

    public Firestore getFireStore(){
        return firestore;
    }

    public void listen(String documentId, final FirebaseControllerObserver controller) {
        DocumentReference docRef = this.colRef.document(documentId);
        docRef.addSnapshotListener((new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    controller.update(snapshot);
                } else {
                }
            }
        }));
    }

    public void playerListen(String player, final FirebaseControllerObserver controller) {
        DocumentReference docRef = gameRef.collection("Players").document(player);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    controller.update(snapshot);
                }
            }
        });
    }

    public void timerListen(final FirebaseControllerObserver controller) {
        DocumentReference docRef = gameRef.collection("Extras").document("Timer");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {

                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    controller.update(snapshot);
                }
            }
        });
    }

    //AreaRegister
    public void AreaListener(String areaId, final FirebaseControllerObserver controller) {
        DocumentReference docRef = gameRef.collection("Areas").document(areaId);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.exists()) controller.update(snapshot);
            }
        });
    }

    // create a lobby
    public void createLobby(int playerAmount, String lobbyNaam, String name) {
        HashMap<String, Object> lobbySettings = new HashMap<>();
        lobbySettings.put("Naam", lobbyNaam);
        lobbySettings.put("Amount", playerAmount);
        lobbySettings.put("begin", false);
        lobbySettings.put("player1", name);
        IntStream.range(2, playerAmount).forEach(i -> lobbySettings.put("player"+i, null));
        firestore.collection("Lobby").document(lobbyNaam).set(lobbySettings);

        createTimer(lobbyNaam);
        setAreas(lobbyNaam);
        makeShop(lobbyNaam);
    }

    //Is er nog plek in deze lobby?
    public int joinLobby(String lobbyNaam, String Name) {
        DocumentReference docRef = firestore.collection("Lobby").document(lobbyNaam);
        DocumentSnapshot doc = getDocSnapshot(firestore.collection("Lobby").document(lobbyNaam));
        Map<String, Object> lobbySet = doc.getData();
        for (int i = 1; i < 5; i++) {
            if (lobbySet.get("player" + i) == null) {
                docRef.update("player" + i, Name);
                return i;
            }
        }
        return 0;
    }

    public void resetTimer(Map<String, Object> info) {
        gameRef.collection("Extras").document("Timer").set(info);
    }

    public void leaveLobby(String lobbyNaam, String Name) {
        DocumentReference docRef = firestore.collection("Lobby").document(lobbyNaam);
        DocumentSnapshot doc = null;
        try {
            doc = docRef.get().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Map<String, Object> lobbySet = doc.getData();
        for (int i = 1; i < 5; i++) {
            if (lobbySet.get("player" + i).equals(Name)) {
                docRef.update("player" + i, null);
                if (i == 1){
                    firestore.collection("Lobby").document(lobbyNaam).delete();
                    colRef.document(lobbyNaam).delete();
                }
                return;
            }
        }
    }


    //InLobbyListener
    public void inLobbyListener(String lobbyName, final FirebaseControllerObserver controller) {
        DocumentReference docRef = firestore.collection("Lobby").document(lobbyName);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    controller.update(snapshot);
                }
            }
        });
    }

    //InLobbyListener
    public void shopListener(final FirebaseControllerObserver controller) {
        DocumentReference docRef = gameRef.collection("Extras").document("Shop");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    controller.update(snapshot);
                }
            }
        });
    }


    //Werkt wel niet toegepast, runtime items toevoegen vind javafx niet leuk.
    //LobbyListener
    public void LobbyListener(final FirebaseLobbyObserver controller) {
        CollectionReference docRef = firestore.collection("Lobby");
        docRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                List<String> buttonLijst = new ArrayList<>();
                for (DocumentSnapshot doc : snapshot.getDocuments()) {
                    buttonLijst.add(doc.getId());
                }
                controller.update(buttonLijst);
            }
        });
    }

    // retrieves active lobbies
    public List<String> getActiveLobbies() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> querys = firestore.collection("Lobby").get();
        QuerySnapshot query = querys.get();
        List<String> namen = new ArrayList<>();
        for (QueryDocumentSnapshot QDoc : query) {
            namen.add(QDoc.getId());
        }
        return namen;
    }

    //player Updates fiches only
    public void playerUpdateFiches(String player, int fichesCount) {
        DocumentReference docRef = gameRef.collection("Players").document(player);
        docRef.update("fiches", fichesCount);
    }

    //set player data
    public void playerUpdate(String id, Map<String, Object> info) {
        gameRef.collection("Players").document(id).update(info);
    }


    //areaUpdates
    public void areaUpdateFiches(String areaId, int count) {
        DocumentReference docRef = gameRef.collection("Areas").document(areaId);
        docRef.update("fiches", count);
    }

    private void makeShop(String lobbyName){
        Map<String, Object> map = new HashMap<>();
        map.put("bought", null);
        colRef.document(lobbyName).collection("Extras").document("Shop").set(map);
    }

    public void boughShop(int item){
        Map<String, Object> map = new HashMap<>();
        map.put("bought", item);
        gameRef.collection("Extras").document("Shop").set(map);
    }

    public void changePointsPlayer(String id, int amount){
        gameRef.collection("Players").document(id).update("punten", amount);
    }

    //Areas setten in firebase
    private void setAreas(String lobbyName) {
        List<QueryDocumentSnapshot> list = null;
        list = getQuerySnapshot(firestore.collection("Maps").document("4PlayerMap").collection("Areas").get()).getDocuments();
        Map<String, Object> map = new HashMap<>();
        for(QueryDocumentSnapshot iets : list){
            map.put("fiches", iets.get("fiches"));
            colRef.document(lobbyName).collection("Areas").document(iets.getId()).set(map);
        }
    }

    public DocumentSnapshot getAreaInfo(String areaId){
        return getDocSnapshot(firestore.collection("Maps").document("4PlayerMap").collection("Areas").document(areaId));
    }

    public void startGame(String lobbyNaam) {
        firestore.collection("Lobby").document(lobbyNaam).update("begin", true);
        setGame(lobbyNaam);

        TimerTask deleteLobby = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> firestore.collection("Lobby").document(lobbyNaam).delete());
            }
        };
        Timer timer = new Timer();
        timer.schedule(deleteLobby, 5000);
    }




    public void registerPlayer(String playerId, Map<String, Object> info){
        gameRef.collection("Players").document(playerId).set(info);
    }

    private void createTimer(String lobbyName){
        Map<String, Object> info = new HashMap<>();
        info.put("endPhase", false);
        info.put("time", 10);
        colRef.document(lobbyName).collection("Extras").document("Timer").set(info);
    }

    public void updateTimer(boolean endPhase, int time){
        Map<String, Object> info = new HashMap<>();
        info.put("endPhase", endPhase);
        info.put("time", time);
        gameRef.collection("Extras").document("Timer").set(info);
    }

    // Ophalen caan de speler naam samen met het aantal punten die de speler heeft gehaald.
    public TreeMap<Double, String> getTop3Player(){
        TreeMap<Double, String> map = new TreeMap<>();
        QuerySnapshot players = getQuerySnapshot(gameRef.collection("Players").get());
        for(QueryDocumentSnapshot qDoc: players.getDocuments()){
            map.put(qDoc.getDouble("punten"), qDoc.getString("Name"));
        }
        return map;
    }

    public void set(String documentId, Map<String, Object> docData) {
        this.colRef.document(documentId).set(docData);
    }

    public DocumentSnapshot getColRef(String documentId) {

        DocumentReference docRef = this.colRef.document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = getDocSnapshot(docRef);

        if (document.exists()) return document;
        return null;

    }

    public void delete(String documentId) {
        this.colRef.document(documentId).delete();
    }

    public boolean login(@NotNull String username, @NotNull String password) {
        DocumentReference docRef = firestore.collection("Accounts").document(username);
        DocumentSnapshot document = getDocSnapshot(docRef);
        if (document.exists()) return document.get("password").equals(password);
        return false;
    }

    public boolean register(String username, String password) {
        DocumentReference docRef = firestore.collection("Accounts").document(username);
        DocumentSnapshot document = getDocSnapshot(docRef);
        if (document.exists()) return false;
        Map<String, String> pass = new HashMap<>();
        pass.put("password", password);
        firestore.collection("Accounts").document(username).set(pass);
        return true;
    }

    private DocumentSnapshot getDocSnapshot(DocumentReference docRef) {
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private QuerySnapshot getQuerySnapshot(ApiFuture<QuerySnapshot> querySnapshotApiFuture){
        try {
            return querySnapshotApiFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}


