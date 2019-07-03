package View;

import Enums.AreaProperty;
import Enums.View3DEnum;
import Objects.NormalFXMLLoader;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;

public class SpecialProperty {

    private AreaProperty specialProp;

    public SpecialProperty(AreaProperty specialProp){
        this.specialProp = specialProp;
    }

    public Group create(){
        System.out.println(specialProp.toString());
        Group mesh = new NormalFXMLLoader("/3dObjects/" + specialProp.toString() + ".fxml").loadGroup();
        View3DEnum.SPECIALPROP.getGroup().getChildren().add(mesh);
        return mesh;
    }

}
