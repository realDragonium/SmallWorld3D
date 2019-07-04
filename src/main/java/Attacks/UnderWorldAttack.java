package Attacks;

import Controller.AreaController;
import Enums.AreaProperty;
import Enums.AreaType;
import Model.CombinationModel;

import java.util.*;

public class UnderWorldAttack implements AttackableAreas {

    @Override
    public List<AreaController> checkAttackableAreas(CombinationModel combi, Collection<AreaController> allAreas) {
        List<AreaController> areas = new ArrayList<>();
        List<AreaController> caves = new ArrayList<>();
        Set<AreaController> areaSet = new HashSet<>();
        List<AreaType> areaTypes = combi.attackableType.getAttackableTypes();

        getAllCaves(allAreas, caves);

        combi.areas.forEach(area -> {
            if (area.getSpecialProp() == AreaProperty.Cave) areaSet.addAll(caves);
            areaSet.addAll(area.getNeighbourCons());
        });

        areaSet.forEach(area -> {
            if (areaTypes.contains(area.getAreaType()) && area.isAttackAble())
                areas.add(area);
        });

        //Filter je eigen areas eruit
        areas.removeAll(combi.areas);

        return areas;
    }

    public void getAllCaves(Collection<AreaController> allAreas, List<AreaController> caves) {
        allAreas.forEach(area -> {
            if (area.isAttackAble() && area.getSpecialProp() == AreaProperty.Cave) {
                caves.add(area);
            }
        });


    }
}
