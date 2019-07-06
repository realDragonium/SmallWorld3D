package Firebase;


import com.google.cloud.firestore.QuerySnapshot;

public interface FirebaseAllLobbiesObserver {

    void update(QuerySnapshot qs);
}
