package Controller;

import Firebase.FirebaseActionObserver;
import Firebase.FirebaseGameObserver;
import Firebase.FirebaseGameService;
import FirebaseActions.FirebaseAction;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseGameController implements FirebaseActionObserver{

    private GameController gameCon;
    private FirebaseGameService service;
    private Map<String, FirebaseGameObserver> observers = new HashMap<>();

    public FirebaseGameController(String gameName, GameController gameCon) {
        this.gameCon = gameCon;
        service = new FirebaseGameService(gameName);
        turnActionListener(this);
    }


    public void placeTurnAction(Object object){
        service.placeAction(object);
    }

    public void turnActionListener(final FirebaseActionObserver controller){
        service.actionListener(controller);
    }

    public void register(String id, FirebaseGameObserver observer ){
        observers.put(id, observer);
    }

    @Override
    public void update(QuerySnapshot qs) {
        System.out.println("update received!");
        List<DocumentChange> testList = qs.getDocumentChanges();
        FirebaseAction action = testList.get(0).getDocument().toObject(FirebaseAction.class);
        action.doAction(gameCon);
    }
}
//        System.out.println("update received!");
//        List<DocumentChange> testList = qs.getDocumentChanges();
//        System.out.println(testList.get(0).getDocument().getId());
//        FirebaseAction action = testList.get(0).getDocument().toObject(FirebaseAction.class);
//        action.doAction(gameCon);

//        List<QueryDocumentSnapshot> docs = qs.getDocuments();
//        QueryDocumentSnapshot qDoc = docs.get(docs.size()-1);
//        System.out.println(qDoc.getId());
//        FirebaseAction action = qDoc.toObject(FirebaseAction.class);
//        action.doAction(this);
