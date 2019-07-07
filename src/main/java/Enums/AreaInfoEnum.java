package Enums;

import javafx.scene.Group;


public enum AreaInfoEnum {
    ATTACK(GameViewEnum.ATTACK.getGroup()), REDEPLOY(GameViewEnum.REDEPLOY.getGroup()),
    LEAVE(GameViewEnum.LEAVE.getGroup()), NONE(new Group()),
    POWERSPATT(GameViewEnum.POWERSPATT.getGroup());


    private Group group;
    AreaInfoEnum(Group group){
        this.group = group;
    }

    public Group getGroup(){
        return group;
    }
}
