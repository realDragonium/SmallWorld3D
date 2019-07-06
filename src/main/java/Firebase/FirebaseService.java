package Firebase;

import com.google.cloud.firestore.Firestore;

public class FirebaseService implements Runnable {

    private Firestore fb;
    private FirebaseLobbyService fbLobby;
    private FirebaseGameService fbGame;

    @Override
    public void run() {
        Database db = new Database();
        fb = db.getFirestoreDatabase();
        fbLobby = new FirebaseLobbyService(fb);
        fbGame = new FirebaseGameService(fb);
        System.out.println("Created FIREBASE");
    }

    public Firestore getFirestore(){
        return fb;
    }

    public FirebaseLobbyService getfbLobby(){
        return fbLobby;
    }

    public FirebaseGameService getfbGame(){
        return fbGame;
    }
}
