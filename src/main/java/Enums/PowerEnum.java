package Enums;

import Power.*;

public enum PowerEnum {
    alchemist(new Alchemist()), berserk(new Berserk()), bivouacking(new Bivouacking()),
    commando(new Commando()), diplomat(new Diplomat()), dragonmaster(new Dragonmaster()),
    flying(new Flying()), forest(new Forest()), fortified(new Fortified()), heroic(new Heroic()),
    hill(new Hill()), merchant(new Merchant()), mounted(new Mounted()), pillaging(new Pillaging()),
    seafaring(new Seafaring()), spirit(new Spirit()), stout(new Stout()), swamp(new Swamp()),
    underworld(new Underworld()), wealthy(new Wealthy());

    private Power power;

    PowerEnum(Power power){
        this.power = power;
    }

    public Power getPower(){
        return power;
    }
}
