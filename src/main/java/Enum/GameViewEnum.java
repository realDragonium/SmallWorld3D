package Enum;

import javafx.scene.Group;

public enum GameViewEnum {
    MAP2D(new Group()), SHOP(new Group()), MAP3D(new Group()), PLAYER(new Group()),
    ROUND(new Group()), TURN(new Group()), PHASE(new Group()), BUTTON(new Group()),
    TIMER(new Group()), VERVAL(new Group()), DICE(new Group()),
    REDEPLOY(new Group()), INFO(new Group()),AREAINFO(new Group()),
    UIOVERLAY(new Group());
    private Group view;

    GameViewEnum(Group view){
        this.view = view;
    }

    public Group getGroup(){
        return view;
    }
}
