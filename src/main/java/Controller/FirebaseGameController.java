package Controller;

import Firebase.FireBaseObjectTest;
import Firebase.FirebaseGameService;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseGameController {

    private FirebaseGameService service;

    public FirebaseGameController(String gameName){
        service  = new FirebaseGameService("test");
//        teester();
    }

    private void teester(){
        Map<String, Object> map = new HashMap<>();
        map.put("item0", new FireBaseObjectTest("test0", "test0"));
        map.put("item1", new FireBaseObjectTest("test1", "test1"));
        map.put("item2", new FireBaseObjectTest("test2", "test2"));
        map.forEach((s, o) -> service.setTest(s, o));

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

    public void shopUpdate(ShopController shop){
        List<CombinationController> items = shop.getShopItems();
        List<Pair<String, String>> itemsFB = new ArrayList<>();
        for(CombinationController con : items){
            itemsFB.add(new Pair<>(con.getRace().getId(),
            con.getPowerOld().getId()));
        }
    }



    public void getAreaInfo(String id){
        Pair<String, String> pair = new Pair<>("test", "test");

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
