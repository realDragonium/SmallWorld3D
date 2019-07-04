package Attacks;

import Controller.AreaController;
import Controller.CombinationController;
import Enums.AreaType;
import Model.CombinationModel;

import java.util.*;

public class NormalAreasAttack implements AttackableAreas {

    private List<AreaController> areas = new ArrayList<>();
    private Collection<AreaController> allAreas;
    private CombinationModel combi;

    @Override
    public List<AreaController> checkAttackableAreas(CombinationModel combi, Collection<AreaController> allAreas) {
        this.allAreas = allAreas;
        this.combi = combi;
        if(combi.areas.size() == 0) borderAreas();
        else normalAreas();

        return areas;
    }

    private void borderAreas(){
        List<AreaType> areaTypes = combi.attackableType.getAttackableTypes();
        allAreas.forEach(area -> {
            if (area.isBorderArea() && area.isAttackAble()
                    && areaTypes.contains(area.getAreaType())) {
                areas.add(area);
            }
        });
    }

    private void normalAreas(){
        Set<AreaController> areaSet = new HashSet<>();
        List<AreaType> areaTypes = combi.attackableType.getAttackableTypes();

        //Voeg alle buren ids toe aan een Set
        combi.areas.forEach(area -> {
            areaSet.addAll(area.getNeighbourCons());
        });

        //Filter alle niet aanvalbare  en misschien water eruit.
        areaSet.forEach(area -> {
            if(areaTypes.contains(area.getAreaType()) &&  area.isAttackAble())
                areas.add(area);
        });

        //Filter je eigen areas eruit
        areas.removeAll(combi.areas);
    }

}
