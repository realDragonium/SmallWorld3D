package View;

import Controller.Map2DController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Map2DView{

	private Map2DController mapCon;
	Group group;

	@FXML
	private Pane pane;

	public Map2DView(Map2DController mapCon, Group group) {
		this.mapCon = mapCon;
		this.group = group;
	}

	public void initialize(){
		group.getChildren().add(pane);
		makeAreas();
	}

	
	private void makeAreas() {
        for (Node area : pane.getChildren()) {
        	mapCon.createArea((Group) area);
        }
	}

}
