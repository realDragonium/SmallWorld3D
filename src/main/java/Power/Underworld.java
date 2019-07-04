package Power;

import Attacks.AreaPropertyAttackBoost;
import Attacks.UnderWorldAttack;
import Enums.AreaProperty;
import Model.CombinationModel;

public class Underworld implements Power {

    @Override
    public String getName() {
        return "underworld";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }

    @Override
    public void activatePower(CombinationModel combi) {
        combi.powerAttackBoost = new AreaPropertyAttackBoost(AreaProperty.Cave);
        combi.powerAreas = new UnderWorldAttack();
    }

}
