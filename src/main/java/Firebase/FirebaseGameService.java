package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseGameService {

    private Firestore fb;
    private DocumentReference gameRef;
    private String gameName;

    public FirebaseGameService(Firestore fb) {
        this.fb = fb;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }


    public void startFBService(){
        gameRef = fb.collection("Games").document(gameName);
    }


    public void placeAction(String number, Object object){
        gameRef.collection("Actions").document(number).set(object);
    }

    public int getLastestEventNumber(){
        List<QueryDocumentSnapshot> list = getQuerySnapshot(gameRef.collection("Actions")).getDocuments();
        return list.size();
    }

    public void actionListener(final FirebaseActionObserver controller) {
        CollectionReference docRef = gameRef.collection("Actions");
        docRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.getDocuments().size() > 0) {
                    controller.update(snapshot);
                }
            }
        });
    }



    public void placeVote(Object vote){
        gameRef.collection("Votes").document().set(vote);
    }

    public void deleteVotes(){
        List<QueryDocumentSnapshot> list = getQuerySnapshot(gameRef.collection("Votes")).getDocuments();
        for(QueryDocumentSnapshot qDocSS: list)
            delete(qDocSS.getId());

    }


    private void delete(String documentId) {
        gameRef.collection("Votes").document(documentId).delete();
    }



    public void voteListener(final FirebaseActionObserver controller) {
        CollectionReference docRef = gameRef.collection("Votes");
        docRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.getDocuments().size() > 0) {
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

}
