package Controller;

import Enums.PowerEnum;
import Enums.RaceEnum;
import Firebase.FirebaseAllLobbiesObserver;
import Firebase.FirebaseLobbyObserver;
import Firebase.FirebaseLobbyService;
import com.google.cloud.firestore.DocumentReference;

import java.util.*;

public class FirebaseLobbyController {

    private FirebaseLobbyService service;
    private DocumentReference currentLobby;


    public void setService(FirebaseLobbyService service){
        this.service = service;
    }

    void changeLobbyInfo(Object info){
        currentLobby.update((Map)info);
    }

    void joinLobby(String id){
        currentLobby = service.joinLobby(id);
    }

    private int getHighestlobbyNumber(){
        return service.getHighestlobbyNumber();
    }

    int createLobby(){
        int id = getHighestlobbyNumber() + 1;
        currentLobby = service.createLobby(Integer.toString(id));
        currentLobby.set(newLobby());
        createStartingShopItems(id);
        return id;
    }

    private Object newLobby() {
        HashMap<String, Object> info = new HashMap<>();
        info.put("gameSpeed", "normal");
        info.put("password", "");
        info.put("inProgress", false);
        HashMap<String, String> names = new HashMap<>();
        names.put("player1", "");
        names.put("player2", "");
        names.put("player3", "");
        names.put("player4", "");
        info.put("playerNames", names);
        HashMap<String, Boolean> states = new HashMap<>();
        states.put("player1", false);
        states.put("player2", false);
        states.put("player3", false);
        states.put("player4", false);
        info.put("playerStates", states);
        return info;
    }

    void leaveLobby(String player) {
        Map info = service.getDocumentSnapshot().getData();
        ((HashMap)info.get("playerNames")).put(player, "");
        ((HashMap)info.get("playerStates")).put(player, false);
        changeLobbyInfo(info);
        currentLobby = null;
    }

    void pushDocumentUpdate(FirebaseLobbyObserver lobby){
        service.pushDocumentUpdate(lobby);
    }

    void actionDocumentListener(FirebaseLobbyObserver controller){
        service.actionDocumentListener(controller);
    }


    void pushAllLobbiesUpdate(FirebaseAllLobbiesObserver lobbies){
        service.pushAllLobbiesUpdate(lobbies);
    }

    public void createStartingShopItems(int id){
        List<String> races = new ArrayList<>();
        List<String> powers = new ArrayList<>();
        Arrays.asList(PowerEnum.values()).forEach(power -> powers.add(power.getPower().getName()));
        Arrays.asList(RaceEnum.values()).forEach(race -> races.add(race.getRace().getName()));
        races.remove("losttribes");
        removeRacesAndPowerWhoDontWork(races, powers);
        for (int i = 0; i < 6; i++) {
            String race = races.get((int) (Math.random() * races.size()));
            String power = powers.get((int) (Math.random() * powers.size()));
            races.remove(race);
            powers.remove(power);
            Map<String, Object> map = new HashMap<>();
            map.put("id", "add");
            map.put("race", race);
            map.put("power", power);
            service.placeStartingCombo(id, i, map);
        }
    }

    public void removeRacesAndPowerWhoDontWork(List<String> races, List<String> powers){
        races.remove("sorcerers");
        races.remove("trolls");
        races.remove("amazones");
        races.remove("halflings"); // Werken half
        powers.remove("bivouacking");
        powers.remove("fortified");
        powers.remove("heroic");
        powers.remove("diplomat");
        powers.remove("dragonmaster");
    }

}
