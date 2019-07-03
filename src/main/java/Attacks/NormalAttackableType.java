package Attacks;

import Enums.AreaType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NormalAttackableType implements AttackableType{

    @Override
    public List<AreaType> getAttackableTypes() {
        List<AreaType> list = new LinkedList<>(Arrays.asList(AreaType.values()));
        list.remove(AreaType.water);
        return list;
    }
}
