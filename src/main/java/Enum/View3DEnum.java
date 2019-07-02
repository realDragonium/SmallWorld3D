package Enum;

import javafx.scene.Group;

public enum View3DEnum {
    SPECIALPROP(new Group()), CAMERA(new Group()), FICHES(new Group()), TABLE(new Group()),
    CRYSTAL(new Group());

    private Group group;
    View3DEnum(Group group){
        this.group = group;
    }

    public Group getGroup(){
        return group;
    }
}
