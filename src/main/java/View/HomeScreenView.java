package View;

import Controller.HomeScreenController;
import Observable.HomeScreenObservable;
import Observer.HomeScreenObserver;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HomeScreenView implements HomeScreenObserver{

	private Pane pane;
	private Group group;
	private HomeScreenController hsCon;

	@FXML
	public Pane root;

    public HomeScreenView(HomeScreenController hsController, Group group){
    	this.group = group;
        hsCon = hsController;
        hsCon.register(this);
    }

	public void initialize() {
    	group.getChildren().add(root);
	}

    @FXML
    private void goToLobby(){
		hsCon.goToLobby();
	}

	@FXML
    private void enterHoverTitle(MouseEvent e) {
    	for(Node node2 : ((HBox) e.getSource()).getChildren()) {
	    	if(node2.getId().equals("Power")) {
		    	TranslateTransition translateTransition = new TranslateTransition(); 
				translateTransition.setDuration(Duration.millis(100));
				translateTransition.setByX(-50 - (node2).getTranslateX());
				translateTransition.setCycleCount(1); 
				translateTransition.setAutoReverse(false); 
				translateTransition.setNode(node2);
				translateTransition.play();
	    	}
			if(node2.getId().equals("Ras")) {
				TranslateTransition translateTransition2 = new TranslateTransition(); 
				translateTransition2.setDuration(Duration.millis(100));
				translateTransition2.setByX(10 - ( node2).getTranslateX());
				translateTransition2.setCycleCount(1); 
				translateTransition2.setAutoReverse(false); 
				translateTransition2.setNode(node2);
				translateTransition2.play();
			}
	    }
    }
	
    @FXML
	private void exitHoverTitle(MouseEvent e) {
		for(Node node2 : ((HBox)  e.getSource()).getChildren()) {
	    	if(node2.getId().equals("Power")) {
		
				TranslateTransition translateTransition = new TranslateTransition(); 
				translateTransition.setDuration(Duration.millis(50));
				translateTransition.setByX(-(((HBox)  e.getSource()).getChildren().get(0)).getTranslateX());
				translateTransition.setCycleCount(1); 
				translateTransition.setAutoReverse(false); 
				translateTransition.setNode((((HBox)  e.getSource()).getChildren().get(0)));
				translateTransition.play();
	    	}
	    	if(node2.getId().equals("Ras")) {
				TranslateTransition translateTransition2 = new TranslateTransition(); 
				translateTransition2.setDuration(Duration.millis(50));
				translateTransition2.setByX(-(node2).getTranslateX());
				translateTransition2.setCycleCount(1); 
				translateTransition2.setAutoReverse(false); 
				translateTransition2.setNode(node2);
				translateTransition2.play();
	    	}
		}
	}


	@Override
	public void update(HomeScreenObservable mvo) {
	}

	public void setPane(Pane pane) {
    	this.pane = pane;
    	pane.getChildren().addAll(root);
    }


	public void readGuide(MouseEvent mouseEvent) {
    	hsCon.readGuide();
	}

	public void logout(){
    	hsCon.logout();
	}
}
