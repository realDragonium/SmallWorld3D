package View;

import Controller.Map3DController;
import Objects.Xform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;

import java.io.IOException;

public class TableView {

    Group root;
    Xform xForm = new Xform();
    Map3DController mapCon;

    public TableView(Group map) {
        root = map;
        this.mapCon = mapCon;
        loadTable();
    }

    public void loadTable(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/3dObjects/table.fxml"));
            MeshView table = fxmlLoader.load();
            PhongMaterial material = (PhongMaterial)table.getMaterial();
            material.setDiffuseColor(Color.rgb(77, 59, 34));
            table.setMaterial(material);
            table.setTranslateY(390);
            xForm.getChildren().add(table);
            xForm.setScaleX(220);
            xForm.setScaleY(220);
            xForm.setScaleZ(220);

//            xForm.setRotateX(180);
            root.getChildren().add(xForm);

            // ...
        }
        catch (IOException e) {
            // exception handling
        }
    }
}
