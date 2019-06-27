package Firebase;

import com.google.cloud.firestore.DocumentSnapshot;

public interface FirebaseControllerObserver {

    void update(DocumentSnapshot ds);
}
