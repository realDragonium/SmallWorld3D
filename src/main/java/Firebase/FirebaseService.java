package Firebase;

import Controller.FirebaseGameController;
import Controller.FirebaseLobbyController;
import com.google.cloud.firestore.Firestore;

public class FirebaseService implements Runnable {

    private Firestore fb;
    private FirebaseLobbyController fbLobby;
    private FirebaseGameController fbGame;

    public FirebaseService(){
        fbLobby = new FirebaseLobbyController();
        fbGame = new FirebaseGameController();
    }

    @Override
    public void run() {
        Database db = new Database();
        fb = db.getFirestoreDatabase();
        fbLobby.setService(new FirebaseLobbyService(fb));
        fbGame.setService(new FirebaseGameService(fb));
    }

    public Firestore getFirestore(){
        return fb;
    }

    public FirebaseLobbyController getfbLobby(){
        return fbLobby;
    }

    public FirebaseGameController getfbGame(){
        return fbGame;
    }
}
