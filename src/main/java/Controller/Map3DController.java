package Controller;

import View.Area3dView;
import javafx.scene.Node;
import javafx.scene.transform.Translate;

import java.util.HashMap;
import java.util.Map;

public class Map3DController {

    Controller3D con3D;
    GameController gameCon;
    Map<String, Translate> areaPoints = new HashMap<>();

    Map3DController(Controller3D con3D, GameController gameCon){
        this.gameCon = gameCon;
        this.con3D = con3D;
        setupAreaPoints();
    }

    public void createArea(Node area, String areaId) {
        AreaController areaCon = new AreaController(areaId, this,areaPoints.get(area.getId()) , gameCon);
        new Area3dView(area, areaCon, con3D);
    }

    private void setupAreaPoints(){
        areaPoints.put("farm_005" ,new Translate(402,-1,363));
        areaPoints.put("hill_004" ,new Translate(409,-1,217));
        areaPoints.put("mountain_004" ,new Translate(343,-74,94));
        areaPoints.put("farm_004" ,new Translate(411,-1,-9));
        areaPoints.put("swamp_007" ,new Translate(443,-1,-125));
        areaPoints.put("hill_003" ,new Translate(434,-1,-262));
        areaPoints.put("water_003" ,new Translate(442,30,-446));
        areaPoints.put("forest_006" ,new Translate(358,-1,-377));
        areaPoints.put("mountain_006" ,new Translate(190,-68,-411));
        areaPoints.put("mountain_005" ,new Translate(252,-57,-271));
        areaPoints.put("hill_002" ,new Translate(317,-1,-105));
        areaPoints.put("forest_005" ,new Translate(212,-1,-147));
        areaPoints.put("swamp_003" ,new Translate(216,-1,7));
        areaPoints.put("forest_004" ,new Translate(186,-1,119));
        areaPoints.put("swamp_006" ,new Translate(259,-1,211));
        areaPoints.put("mountain_003" ,new Translate(167,-87,303));
        areaPoints.put("farm_006" ,new Translate(248,-1,425));
        areaPoints.put("forest_008" ,new Translate(126,-1,456));
        areaPoints.put("mountain_009" ,new Translate(88,-86,201));
        areaPoints.put("farm_003" ,new Translate(108,-1,-221));
        areaPoints.put("forest_007" ,new Translate(77,19,-361));
        areaPoints.put("mountain_007" ,new Translate(12,-69,-452));
        areaPoints.put("hill_001" ,new Translate(-134,-1,-412));
        areaPoints.put("swamp_002" ,new Translate(-36,10,-287));
        areaPoints.put("water_002" ,new Translate(111,30,-71));
        areaPoints.put("hill_007" ,new Translate(-10,-1,52));
        areaPoints.put("farm_007" ,new Translate(-35,-1,176));
        areaPoints.put("hill_005" ,new Translate(33,-1,315));
        areaPoints.put("mountain_008" ,new Translate(-113,-102,-87));
        areaPoints.put("hill_008" ,new Translate(-177,-27,-178));
        areaPoints.put("farm_002" ,new Translate(-165,-1,-314));
        areaPoints.put("forest_001" ,new Translate(-275,-1,-417));
        areaPoints.put("forest_002" ,new Translate(-319,-1,-272));
        areaPoints.put("swamp_008" ,new Translate(-317,-1,-137));
        areaPoints.put("farm_009" ,new Translate(-225,-1,-22));
        areaPoints.put("swamp_004" ,new Translate(-139,-1,69));
        areaPoints.put("hill_006" ,new Translate(-184,-1,188));
        areaPoints.put("farm_008" ,new Translate(-99,-1,304));
        areaPoints.put("forest_009" ,new Translate(-264,-1,74));
        areaPoints.put("swamp_001" ,new Translate(-393,26,-404));
        areaPoints.put("farm_001" ,new Translate(-420,25,-203));
        areaPoints.put("forest_003" ,new Translate(-440,-1,-92));
        areaPoints.put("water_001" ,new Translate(-405,30,22));
        areaPoints.put("hill_009" ,new Translate(-373,-1,102));
        areaPoints.put("mountain_002" ,new Translate(-308,-87,417));
        areaPoints.put("swamp_009" ,new Translate(-243,-1,319));
        areaPoints.put("mountain_001" ,new Translate(-34,-84,409));
        areaPoints.put("swamp_005" ,new Translate(-147,-1,437));
    }

    void placeFiche(AreaController areaCon, FicheController fiche) {
        areaCon.putFiche(fiche);
    }
}
