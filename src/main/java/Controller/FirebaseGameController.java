package Controller;

import Firebase.FirebaseGameService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseGameController {

    private FirebaseGameService service;

    public FirebaseGameController(String gameName){
        service  = new FirebaseGameService(gameName);
//        testPlayerUpdate("player1");
    }

    private void testPlayerUpdate(String playerId){
        Map<String, Object> map = new HashMap<>();
        List<String> lijst = new ArrayList<>();
        lijst.add("test4");
        lijst.add("test5");
        lijst.add("test6");
        map.put("fiches", 55);
        map.put("points", 50);
        map.put("lijst", lijst);
        service.updatePlayer(playerId, map);
    }

    public void playerUpdate(PlayerController player){
        Map<String, Object> map = new HashMap<>();
        map.put("connected", player.isConnected());
        service.updatePlayer(player.getId(), map);
    }

    public void areaUpdate(AreaController area){
        Map<String, Object> map = new HashMap<>();
        map.put("fiches",area.getFichesAmount());
        service.updateArea(area.getId(), map);
    }

    public void areaListener(AreaController area){
        service.areaListener(area.getId(), area);
    }

    public void playerListener(PlayerController player){
        service.playerListener(player.getId(), player);
    }

    public void shopListener(ShopController shop){
        service.shopListener(shop);
    }

}
