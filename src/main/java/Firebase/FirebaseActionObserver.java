package Firebase;

import com.google.cloud.firestore.QuerySnapshot;

public interface FirebaseActionObserver {

    void update(QuerySnapshot qs);
}
