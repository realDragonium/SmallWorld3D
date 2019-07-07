package Enums;

import javafx.scene.Group;

public enum GameViewEnum {
    MAP2D(new Group()), SHOP(new Group()), MAP3D(new Group()), PLAYER(new Group()),
    ROUND(new Group()), TURN(new Group()), PHASE(new Group()), BUTTON(new Group()),
    TIMER(new Group()), DECLINE(new Group()), DICE(new Group()), ATTACK(new Group()),
    REDEPLOY(new Group()), INFO(new Group()),AREAINFO(new Group()), LEAVE(new Group()),
    UIOVERLAY(new Group()), NOTIFICATION(new Group()), COMBINATION(new Group()),
    PLAYERSINFO(new Group()), VOTE(new Group()), COMBIINFO(new Group()), POWERSPATT(new Group());

    private Group view;

    GameViewEnum(Group view){
        this.view = view;
    }

    public Group getGroup(){
        return view;
    }
}
