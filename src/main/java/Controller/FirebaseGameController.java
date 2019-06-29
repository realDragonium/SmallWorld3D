package Controller;

import Firebase.FirebaseActionObserver;
import Firebase.FirebaseGameObserver;
import Firebase.FirebaseGameService;
import FirebaseActions.FirebaseAction;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseGameController implements FirebaseActionObserver {

    private GameController gameCon;
    private FirebaseGameService service;
    private Map<String, FirebaseGameObserver> observers = new HashMap<>();

    public FirebaseGameController(String gameName, GameController gameCon) {
        this.gameCon = gameCon;
        service = new FirebaseGameService(gameName);
        turnActionListener(this);
    }

    private void placeAction(Map<String, Object> map) {
        service.placeAction(map);
    }

    public void nextPhaseAction() {
        Map<String, Object> map = new HashMap<>();
        map.put("division", "phase");
        map.put("id", "nextPhase");
        map.put("action", "nextPhase");
        placeAction(map);
    }

    public void buyCombiAction(int number) {
        Map<String, Object> map = new HashMap<>();
        map.put("division", "shop");
        map.put("id", "buy");
        map.put("item", number);
        placeAction(map);
    }

    public void addCombiAction(String race, String power) {
        Map<String, Object> map = new HashMap<>();
        map.put("division", "shop");
        map.put("id", "add");
        map.put("race", race);
        map.put("power", power);
        placeAction(map);
    }

    public void attackAction(String areaId) {
        Map<String, Object> map = new HashMap<>();
        map.put("division", "currentplayer");
        map.put("id", "attack");
        map.put("areaId", areaId);
        placeAction(map);
    }

    public void addsFicheAction(String areaId) {
        Map<String, Object> map = new HashMap<>();
        map.put("division", "currentplayer");
        map.put("id", "add");
        map.put("areaId", areaId);
        placeAction(map);
    }

    public void removeFicheAction(String areaId) {
        Map<String, Object> map = new HashMap<>();
        map.put("division", "currentplayer");
        map.put("id", "remove");
        map.put("areaId", areaId);
        placeAction(map);
    }

    public void leavesFicheAction(String areaId) {
        Map<String, Object> map = new HashMap<>();
        map.put("division", "currentplayer");
        map.put("id", "leaves");
        map.put("areaId", areaId);
        placeAction(map);
    }

    public void declineAction() {
        Map<String, Object> map = new HashMap<>();
        map.put("division", "currentplayer");
        map.put("id", "decline");
        map.put("action", "decline");
        placeAction(map);
    }


    private void turnActionListener(final FirebaseActionObserver controller) {
        service.actionListener(controller);
    }

    public void register(String id, FirebaseGameObserver observer) {
        observers.put(id, observer);
    }

    @Override
    public void update(QuerySnapshot qs) {
        System.out.println("update received!");
        List<DocumentChange> updateList = qs.getDocumentChanges();
        for (DocumentChange documentChange : updateList) {
            DocumentSnapshot doc = documentChange.getDocument();
            System.out.println(doc.getData());
            Platform.runLater(() -> observers.get(doc.getString("division")).update(doc));
        }
    }
}
