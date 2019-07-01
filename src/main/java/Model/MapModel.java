package Model;

import Controller.AreaController;
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

    public MapModel(PlayerController player){
        mapPlayer = player;
    }

    public PlayerController getMapPlayer(){
        return mapPlayer;
    }

}
