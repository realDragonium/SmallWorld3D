package Firebase;

import com.google.cloud.firestore.DocumentSnapshot;

public interface FirebaseLobbyObserver {

    void update(DocumentSnapshot ds);
}
