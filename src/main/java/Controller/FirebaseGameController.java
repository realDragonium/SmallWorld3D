package Controller;

import Firebase.FirebaseActionObserver;
import Firebase.FirebaseGameObserver;
import Firebase.FirebaseGameService;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.application.Platform;

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
        service.placeAction(getEventNumber(), map);
    }

    //Met 3 is het grootste aantal
    private String getEventNumber(){
        String id = String.valueOf(service.getLastestEventNumber());
        StringBuilder str = new StringBuilder(id);
        while(str.length() < 3) str.insert(0, "0");
        return str.toString();
    }

    public void nextPhaseAction() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "phase");
        map.put("action", "nextPhase");
        placeAction(map);
    }

    public void buyCombiAction(int number) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "buy");
        map.put("item", number);
        placeAction(map);
    }

    public void addCombiAction(String race, String power) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "add");
        map.put("race", race);
        map.put("power", power);
        placeAction(map);
    }

    public void attackAction(String areaId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "attack");
        map.put("areaId", areaId);
        placeAction(map);
    }

    public void addsFicheAction(String areaId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "addFiche");
        map.put("areaId", areaId);
        placeAction(map);
    }

    public void removeFicheAction(String areaId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "removeFiche");
        map.put("areaId", areaId);
        placeAction(map);
    }

    public void leavesFicheAction(String areaId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "leaves");
        map.put("areaId", areaId);
        placeAction(map);
    }

    public void declineAction() {
        Map<String, Object> map = new HashMap<>();
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
        List<DocumentChange> updateList = qs.getDocumentChanges();
        for (DocumentChange documentChange : updateList) {
            DocumentSnapshot doc = documentChange.getDocument();
            System.out.println("Id: " + doc.getId());
            System.out.println(doc.getData());
            Platform.runLater(() -> observers.get(doc.getString("id")).update(doc));
        }
    }
}
