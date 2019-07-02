package View;

import Enums.View3DEnum;
import Objects.NormalFXMLLoader;
import javafx.scene.Group;

public class SpecialProperty {

    private String specialProp;

    public SpecialProperty(String specialProp){
        this.specialProp = specialProp;
    }

    public Group create(){
        Group group = new NormalFXMLLoader("/3dObjects/" + specialProp + ".fxml").loadGroup();
        View3DEnum.SPECIALPROP.getGroup().getChildren().add(group);
        return group;
    }

}
