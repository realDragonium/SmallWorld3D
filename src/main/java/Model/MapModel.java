package Model;

import Controller.AreaController;
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

}
