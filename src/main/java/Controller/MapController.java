package Controller;

import Model.MapModel;
import Objects.AreaInfo;
import View.Area2DView;
import View.Area3DView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Translate;

import java.awt.geom.Area;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapController {
	private Map<String, AreaController> areas = new HashMap<>();
	private GameController gameCon;
	private MapModel model = new MapModel();
	private Controller3D con3D;

	MapController(GameController gameCon){
		this.gameCon = gameCon;
		loadInAreaInfo();
		setupAreaPoints();
		setupSpecialPropPoints();
		createAreaControllers();
	}

	public void createAreaView(Node area, Group map){
//		new Area2DView(area, areas.get(area.getChildren().get(0).getId()));
		new Area3DView(area, areas.get(area.getId()), map);
	}

	public void createAreaView(Group area){
		new Area2DView(area, areas.get(area.getChildren().get(0).getId()));
	}


	public void loadInAreaInfo(){
		List<AreaInfo> infoList = null;
		try(Reader reader = new InputStreamReader(new FileInputStream("src/main/resources/Map/5playerMapInfo.json"))){
			Type type = new TypeToken<ArrayList<AreaInfo>>(){}.getType();
			infoList = new Gson().fromJson(reader, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, AreaInfo> infoMap = new HashMap<>();
		for(AreaInfo info : infoList){
			infoMap.put(info.id, info);
		}
		model.areaInfos = infoMap;
	}

	public AreaController getAreaCon(String areaId){
		return areas.get(areaId);
	}

	private void createAreaControllers(){
		Map<String, AreaInfo> infoMap = model.areaInfos;

		infoMap.forEach((s, info) -> {
			AreaController areaCon = new AreaController(info, this, gameCon);
			areaCon.setAreaPoint(model.areaPoints.get(info.id));
			areaCon.setPropPoint(model.propPoints.get(info.id));
			areas.put(s, new AreaController(info, this, gameCon));
		});
	}

	public Translate getTranslate(String areaId){
		return model.areaPoints.get(areaId);
	}

	AreaController getActiveArea(){
		return model.activeArea;
	}

	void selectSingleArea(AreaController areaCons){
		if(model.activeArea.equals(areaCons)){
			deleteActive(areaCons);
		}
		else{
			addActive(areaCons);
		}
	}

	private void deleteActive(AreaController areaCon){
		model.activeArea = null;
		areaCon.changeActive();
	}

	private void addActive(AreaController areaCon){
		model.activeArea = areaCon;
		areaCon.changeActive();
	}

	private void setupSpecialPropPoints(){
		Map<String, Translate> propPoints = new HashMap<>();
		propPoints.put("mountain_008" ,new Translate(-57,-59,-67));
		propPoints.put("swamp_001" ,new Translate(-393,27,-388));
		propPoints.put("farm_001" ,new Translate(-466,18,-324));
		propPoints.put("forest_001" ,new Translate(-226,-1,-460));
		propPoints.put("hill_001" ,new Translate(-138,-1,-465));
		propPoints.put("farm_002" ,new Translate(-152,-1,-289));
		propPoints.put("hill_008" ,new Translate(-256,-1,-221));
		propPoints.put("forest_002" ,new Translate(-337,-1,-240));
		propPoints.put("forest_003" ,new Translate(-408,-1,-94));
		propPoints.put("swamp_008" ,new Translate(-297,-1,-99));
		propPoints.put("farm_009" ,new Translate(-267,-1,-57));
		propPoints.put("hill_009" ,new Translate(-414,-1,132));
		propPoints.put("forest_009" ,new Translate(-233,-1,79));
		propPoints.put("swamp_004" ,new Translate(-179,-1,63));
		propPoints.put("hill_006" ,new Translate(-220,-1,155));
		propPoints.put("swamp_009" ,new Translate(-321,-1,354));
		propPoints.put("mountain_002" ,new Translate(-285,-87,404));
		propPoints.put("swamp_005" ,new Translate(-200,-1,404));
		propPoints.put("mountain_001" ,new Translate(-62,-75,393));
		propPoints.put("hill_005" ,new Translate(36,-1,354));
		propPoints.put("farm_008" ,new Translate(-74,-1,253));
		propPoints.put("farm_007" ,new Translate(1,-1,132));
		propPoints.put("hill_007" ,new Translate(20,-1,23));
		propPoints.put("swamp_002" ,new Translate(-75,22,-311));
		propPoints.put("forest_007" ,new Translate(18,17,-370));
		propPoints.put("farm_003" ,new Translate(160,-1,-232));
		propPoints.put("forest_005" ,new Translate(211,-1,-108));
		propPoints.put("swamp_003" ,new Translate(266,-1,5));
		propPoints.put("farm_004" ,new Translate(446,-1,34));
		propPoints.put("swamp_007" ,new Translate(459,-1,-172));
		propPoints.put("hill_003" ,new Translate(441,-1,-294));
		propPoints.put("forest_006" ,new Translate(305,-1,-462));
		propPoints.put("mountain_006" ,new Translate(183,-62,-438));
		propPoints.put("mountain_005" ,new Translate(219,-60,-274));
		propPoints.put("hill_004" ,new Translate(451,-1,234));
		propPoints.put("farm_005" ,new Translate(432,-1,367));
		propPoints.put("farm_006" ,new Translate(325,-1,456));
		propPoints.put("mountain_003" ,new Translate(189,-86,277));
		propPoints.put("mountain_004" ,new Translate(294,-80,76));
		propPoints.put("mountain_007" ,new Translate(-34,-67,-461));
		propPoints.put("swamp_006" ,new Translate(201,-1,209));
		propPoints.put("forest_008" ,new Translate(55,-1,445));
		model.propPoints = propPoints;
	}

	private void setupAreaPoints(){
		Map<String, Translate> areaPoints = new HashMap<>();
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
		model.areaPoints = areaPoints;
	}

	public void placeFiche(AreaController areaCon, FicheController fiche) {
		areaCon.putFiche(fiche);
	}

    public GameController getGameCon() {
		return gameCon;
    }
}
