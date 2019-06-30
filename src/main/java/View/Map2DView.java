package View;

import Controller.MapController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Map2DView{

	private MapController mapCon;
	Group group;

	@FXML
	private Pane pane;

	public Map2DView(MapController mapCon, Group group) {
		this.mapCon = mapCon;
		this.group = group;
	}

	public void initialize(){
		group.getChildren().add(pane);
		makeAreas();
	}

	
	private void makeAreas() {
        for (Node area : pane.getChildren()) {
        	mapCon.createAreaView((Group) area);
        }
	}

}
