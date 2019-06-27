package Enum;

import Race.*;

public enum RaceEnum {
    amazones(new Amazones()), dwarves(new Dwarves()), elves(new Elves()), ghouls(new Ghouls()),
    giants(new Giants()), halflings(new Halflings()), humans(new Humans()),
    losttribes(new Losttribes()), orcs(new Orcs()), ratmen(new Ratmen()), skeletons(new Skeletons()),
    sorcerers(new Sorcerers()), tritons(new Tritons()), trolls(new Trolls()), wizards(new Wizards());

    Race race;

    RaceEnum(Race race){
        this.race = race;
    }

    public Race getRace(){
        return race;
    }
}
