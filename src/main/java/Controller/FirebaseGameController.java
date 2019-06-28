package Controller;

import Firebase.FirebaseGameService;
import Firebase.ShopCombination;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseGameController {

    private FirebaseGameService service;

    public FirebaseGameController(String gameName) {
        service = new FirebaseGameService(gameName);
    }

    public void playerUpdate(PlayerController player) {
        Map<String, Object> map = new HashMap<>();
        map.put("connected", player.isConnected());
        service.updatePlayer(player.getId(), map);
    }

    public void areaUpdate(AreaController area) {
        Map<String, Object> map = new HashMap<>();
        map.put("fiches", area.getFichesAmount());
        service.updateArea(area.getId(), map);
    }

    public void shopUpdate(ShopController shop) {
        Map<String, Object> map = new HashMap<>();
        int counter = 0;
        for (CombinationController combiCon : shop.getShopItems())
            service.updateShop("item" + counter++, new ShopCombination(combiCon.getRaceName(), combiCon.getPowerName()));
    }

    public void buyCombo(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("bought", id);
        service.buyFromShop("item" + id, map);
    }


    List<CombinationController> getShopItems() {
        List<CombinationController> items = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<QueryDocumentSnapshot> documents = service.getShopItems();
        for (DocumentSnapshot document : documents) {
            ShopCombination shopCombi = document.toObject(ShopCombination.class);
            items.add(new CombinationController(shopCombi.getRace(), shopCombi.getPower()));
        }
        return items;
    }


    private DocumentSnapshot getAreaInfo(String areaId) {
        return service.getAreaInfo(areaId);
    }

    String getAreaType(String id){
        return getAreaInfo(id).getString("type");
    }
    @SuppressWarnings("ConstantConditions")
    boolean isAreaBorderArea(String id){
        return getAreaInfo(id).getBoolean("borderArea");
    }
    @SuppressWarnings("unchecked")
    List<String> getNeighboursArea(String id){
        return (List<String>) getAreaInfo(id).get("neighbours");
    }
    @SuppressWarnings("ConstantConditions")
    boolean isAttackable(String id){
        return getAreaInfo(id).getBoolean("attackable");
    }
    @SuppressWarnings("ConstantConditions")
    int basicFichesCount(String id){ return getAreaInfo(id).getDouble("fiches").intValue(); }


    public void areaListener(AreaController area) {
        service.areaListener(area.getId(), area);
    }

    public void playerListener(PlayerController player) {
        service.playerListener(player.getId(), player);
    }

    public void shopListener(ShopController shop) {
        service.shopListener(shop);
    }

}
