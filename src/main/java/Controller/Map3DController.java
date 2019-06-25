package Controller;

import View.Area3dView;
import javafx.scene.Node;

import java.awt.geom.Area;

public class Map3DController {

    Controller3D con3D;
    GameController gameCon;

    public Map3DController(Controller3D con3D, GameController gameCon){
        this.gameCon = gameCon;
        this.con3D = con3D;
    }

    public void createArea(Node area, String areaId) {
        AreaController areaCon = new AreaController(areaId, this, gameCon);
        new Area3dView(area, areaCon);
    }
}
