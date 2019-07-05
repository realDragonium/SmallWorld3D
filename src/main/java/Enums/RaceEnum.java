package Enums;

import Race.*;
import javafx.scene.Group;

public enum RaceEnum {
    amazones(new Amazones(), new Group()), dwarves(new Dwarves(), new Group()),
    elves(new Elves(), new Group()), ghouls(new Ghouls(), new Group()),
    giants(new Giants(), new Group()), halflings(new Halflings(), new Group()),
    humans(new Humans(), new Group()),
    losttribes(new Losttribes(), new Group()), orcs(new Orcs(), new Group()),
    ratmen(new Ratmen(), new Group()), skeletons(new Skeletons(), new Group()),
    sorcerers(new Sorcerers(), new Group()), tritons(new Tritons(), new Group()),
    trolls(new Trolls(), new Group()), wizards(new Wizards(), new Group());

    private Race race;
    private Group group;

    RaceEnum(Race race, Group group){
        this.race = race;
        this.group = group;
    }

    public Race getRace(){
        return race;
    }
    public Group getGroup(){return group;}
}
