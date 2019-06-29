package Controller;

import Firebase.FirebaseActionObserver;
import Firebase.FirebaseGameService;

public class FirebaseGameController {

    private FirebaseGameService service;

    public FirebaseGameController(String gameName) {
        service = new FirebaseGameService(gameName);
    }


    public void placeTurnAction(Object object){
        service.placeAction(object);
    }

    public void turnActionListener(final FirebaseActionObserver controller){
        service.actionListener(controller);
    }
}
