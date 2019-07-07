package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class FirebaseLoginService {

    private Firestore fb;
    private CollectionReference accRef;

    public FirebaseLoginService(Firestore fb) {
        this.fb = fb;
        accRef = fb.collection("Accounts");
    }

    public boolean exists(String name){
        return getDocSnapshot(accRef.document(name)).exists();
    }

    public DocumentSnapshot getPassword(String username) {
        DocumentReference docRef = accRef.document(username);
        return getDocSnapshot(docRef);

    }

    public void register(String username, HashMap<String, Object> map) {
        accRef.document(username).set(map);
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

    public void setLoggedIn(String username, HashMap<String, Object> map) {
        accRef.document(username).update(map);
    }


}
