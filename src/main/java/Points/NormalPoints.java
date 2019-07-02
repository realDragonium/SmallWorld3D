package Points;

import Enums.AreaType;

import java.util.HashMap;
import java.util.Map;

public class NormalPoints implements Points {

    Map<AreaType, Integer> values = new HashMap<>();

    public NormalPoints(){
        values.put(AreaType.farm, 1);
        values.put(AreaType.forest, 1);
        values.put(AreaType.hill, 1);
        values.put(AreaType.water, 1);
        values.put(AreaType.swamp, 1);
        values.put(AreaType.mountain, 1);
    }


    @Override
    public int getWorth(AreaType type) {
        return values.get(type);
    }
}
