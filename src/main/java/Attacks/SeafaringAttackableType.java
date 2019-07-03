package Attacks;

import Enums.AreaType;

import java.util.Arrays;
import java.util.List;

public class SeafaringAttackableType implements AttackableType{

    @Override
    public List<AreaType> getAttackableTypes() {
        return Arrays.asList(AreaType.values());
    }
}
