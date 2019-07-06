package View;

import Controller.LoginController;
import Observable.LoginObservable;
import Observer.LoginObserver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class LoginView implements LoginObserver{

	@FXML
	private Pane root;
	@FXML
	private TextField Username;
	@FXML
	private TextField Password;
	@FXML
	private Button registeerButton;
	@FXML
	private Text failed;

	private Group group;
	private LoginController loginController;

    public LoginView(LoginController loginController, Group group){
    	this.group = group;
		this.loginController = loginController;
    }

	public void initialize() {
		group.getChildren().add(root);
		loginController.register(this);

		ScrollPane scrollPane = new ScrollPane();
		root.getChildren().add(scrollPane);

		AnchorPane anchorPane = new AnchorPane();
		scrollPane.setContent(anchorPane);

		scrollPane.setPrefViewportHeight(220);
		scrollPane.setPrefViewportWidth(100);

		for (int i = 0; i < 10; i++) {
			Pane pane = new Pane();
			Button button = new Button("test"+i);
			button.setMinHeight(40);
			button.setMinWidth(80);
			button.setStyle("-fx-background-color: rgb(46, 59, 69);");
			pane.getChildren().add(button);
			pane.setLayoutY(i* 50);
			pane.setPrefSize(100, 50);
			anchorPane.getChildren().add(pane);
		}

	}

	@FXML
	private void login(){
		loginController.validateLoginInfo(Username.getText(), Password.getText());
//		loginController.goToHomeScreen();
	}

	private void goToHomeScreen(){
    	loginController.goToHomeScreen();
	}

	private void showFailedAttempt(){
		failed.setOpacity(1);
		TimerTask start = new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> failed.setOpacity(0));
			}
		};
		new Timer().schedule(start, 3000);
	}

    @FXML
	private void registeren(){
    	loginController.register(Username.getText(), Password.getText());
	}

	@Override
	public void update(LoginObservable lo) {
		if(!lo.getLoginState()) showFailedAttempt();
		else goToHomeScreen();
	}
}
