package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseGameService {

    private Firestore fb;
    private DocumentReference gameRef;

    public FirebaseGameService(String gameName) {
        Database db = new Database();
        fb = db.getFirestoreDatabase();
        gameRef = fb.collection("Games").document(gameName);
    }

    public void setTest(String itemId, Object object) {
        gameRef.collection("Shop").document(itemId).set(object);
    }

    public void updatePlayer(String playerId, Map<String, Object> map) {
        gameRef.collection("Players").document(playerId).set(map);
    }

    public void updateArea(String playerId, Map<String, Object> map) {
        gameRef.collection("Areas").document(playerId).update(map);
    }

    public void updateShop(String itemId, Object object) {
        gameRef.collection("Shop").document(itemId).set(object);
    }

    public void buyFromShop(String itemId, Map<String, Object> map){
        gameRef.collection("Extra").document("Shop").update(map);
    }

    public List<QueryDocumentSnapshot>  getShopItems() {
        return getQuerySnapshot(gameRef.collection("Shop").get()).getDocuments();
    }

    public DocumentSnapshot getAreaInfo(String areaId){
        return getDocSnapshot(fb.collection("Maps").document("4PlayerMap").collection("Areas").document(areaId));
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
