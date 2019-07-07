package Enums;

import javafx.scene.Group;

public enum ApplicationViewEnum {
    LOGIN(new Group()), GAME(new Group()), HOMESCREEN(new Group()), LOBBY(new Group()),
    INLOBBY(new Group()), LEADER(new Group());

    private Group group;

    ApplicationViewEnum(Group group){
        this.group = group;
    }

    public Group getGroup(){
        return group;
    }
}
