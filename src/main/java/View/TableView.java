package View;

import Objects.Xform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;

import java.io.IOException;

public class TableView {

    private Group root;
    private Xform xForm = new Xform();

    public TableView(Group map) {
        root = map;
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
