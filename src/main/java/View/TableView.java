package View;

import Objects.NormalFXMLLoader;
import Objects.Xform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;

import Enums.View3DEnum;

public class TableView {

    private Group root;
    private Xform xForm = new Xform();

    public TableView() {
        root = View3DEnum.TABLE.getGroup();
        loadTable();
    }

    public void loadTable() {
        MeshView table = new NormalFXMLLoader("/3dObjects/table.fxml").loadMeshView();
        PhongMaterial material = (PhongMaterial) table.getMaterial();
        material.setDiffuseColor(Color.rgb(77, 59, 34));
        table.setMaterial(material);
        table.setTranslateY(390);
        xForm.getChildren().add(table);
        xForm.setScaleX(220);
        xForm.setScaleY(220);
        xForm.setScaleZ(220);
//            xForm.setRotateX(180);
        root.getChildren().add(xForm);
    }
}
