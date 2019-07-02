package Enum;

import javafx.scene.Group;

public enum AreaInfoEnum {
    ATTACK(new Group()), REDEPLOY(new Group()), PREPARE(new Group()),
    NONE(new Group());


    private Group group;
    AreaInfoEnum(Group group){
        this.group = group;
    }

    public Group getGroup(){
        return group;
    }
}
