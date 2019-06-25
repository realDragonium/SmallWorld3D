package View;

import Controller.Map3DController;
import Objects.Xform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.MeshView;

import java.io.IOException;

public class Map3DView {

    Group root;
    Xform xForm = new Xform();
    Map3DController mapCon;

    public Map3DView(Map3DController mapCon, Group map) {
        root = map;
        this.mapCon = mapCon;
        loadMap();
    }

    public void loadMap(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/3dObjects/map.fxml"));
            Group map = fxmlLoader.load();
            for(Node area : map.getChildren()){
                if(!area.getId().equals("nope")) {
                    String areaId = area.getId().substring(14);
                    area.setId(areaId);
                    mapCon.createArea(area, areaId);

                }
            }
            map.setScaleX(100);
            map.setScaleY(100);
            map.setScaleZ(100);
            xForm.getChildren().add(map);
//            xForm.setRotateX(180);
            root.getChildren().add(xForm);

            // ...
        }
        catch (IOException e) {
            // exception handling
        }
    }
}
