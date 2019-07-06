package Firebase;

import Controller.FirebaseGameController;
import Controller.FirebaseLobbyController;
import Controller.FirebaseLoginController;
import com.google.cloud.firestore.Firestore;

public class FirebaseService implements Runnable {

    private Firestore fb;
    private FirebaseLobbyController fbLobby;
    private FirebaseGameController fbGame;
    private FirebaseLoginController fbLogin;

    public FirebaseService(){
        fbLobby = new FirebaseLobbyController();
        fbGame = new FirebaseGameController();
        fbLogin = new FirebaseLoginController();
    }

    @Override
    public void run() {
        Database db = new Database();
        fb = db.getFirestoreDatabase();
        fbLobby.setService(new FirebaseLobbyService(fb));
        fbGame.setService(new FirebaseGameService(fb));
        fbLogin.setService(new FirebaseLoginService(fb));
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

    public FirebaseLoginController getfbLogin(){
        return fbLogin;
    }
}
