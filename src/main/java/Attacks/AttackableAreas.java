package Attacks;

import Controller.AreaController;
import Model.CombinationModel;

import java.util.Collection;
import java.util.List;

public interface AttackableAreas {

    List<AreaController> checkAttackableAreas(CombinationModel combi, Collection<AreaController> allAreas);
}
