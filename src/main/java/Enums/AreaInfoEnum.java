package Enums;

import javafx.scene.Group;


public enum AreaInfoEnum {
    ATTACK(GameViewEnum.ATTACK.getGroup()), REDEPLOY(GameViewEnum.REDEPLOY.getGroup()),
    PREPARE(GameViewEnum.PREPARE.getGroup()), NONE(new Group());


    private Group group;
    AreaInfoEnum(Group group){
        this.group = group;
    }

    public Group getGroup(){
        return group;
    }
}
