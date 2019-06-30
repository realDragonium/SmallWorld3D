package Firebase;

import com.google.cloud.firestore.DocumentSnapshot;

public interface FirebaseGameObserver {

    void update(DocumentSnapshot ds);
}
