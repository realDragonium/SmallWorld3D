package Model;

import Controller.AreaController;
import Controller.CombinationController;
import Controller.GameController;
import Controller.PlayerController;
import Objects.AreaInfo;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapModel {

    public AreaController activeArea;
    public Map<String, AreaInfo> areaInfos;
    public Map<String, Translate> areaPoints;
    public Map<String, Translate> propPoints;
    private PlayerController mapPlayer;
    private CombinationController mapCombi;

    public MapModel(PlayerController player, GameController gameCon){
        mapPlayer = player;
        mapCombi = new CombinationController(gameCon,"losttribes", "flying");
        mapCombi.setPlayer(player);
    }

    public PlayerController getMapPlayer(){
        return mapPlayer;
    }

    public CombinationController getCombi(){
        return mapCombi;
    }

}
