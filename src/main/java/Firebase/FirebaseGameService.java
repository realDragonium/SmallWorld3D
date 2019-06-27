package Firebase;

import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;

import java.util.Map;

public class FirebaseGameService {

    private Firestore fb;
    private DocumentReference gameRef;

    public FirebaseGameService(String gameName) {
        Database db = new Database();
        fb = db.getFirestoreDatabase();
        gameRef = fb.collection("Games").document(gameName);
    }

    public void updatePlayer(String playerId, Map<String, Object> map){
        gameRef.collection("Players").document(playerId).update(map);
    }

    public void updateArea(String playerId, Map<String, Object> map){
        gameRef.collection("Areas").document(playerId).update(map);
    }


    public void areaListener(String areaId, final FirebaseGameObserver controller) {
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

    public void playerListener(String player, final FirebaseGameObserver controller) {
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

    public void shopListener(final FirebaseGameObserver controller) {
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
}
