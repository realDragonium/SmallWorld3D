package View;

import Controller.LobbyController;
import Observable.ObservableLobby;
import Observer.LobbyObserver;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.List;

public class LobbyView implements LobbyObserver {
    private LobbyController lobbyCon;
    private Group group;
    public Group root;
    public Group lobbies;
    public Button back, host;
    public GridPane panel;
    private Button activeButton;
    private int gridCounter = 0;             // startwaarde aantal aangemaakte lobbies = 0
    private GridPane grid= new GridPane();
    private ScrollPane scrollPane = new ScrollPane();
    private Pane pane = new Pane();


    public LobbyView(LobbyController con, Group group) {
        this.group = group;
        this.lobbyCon = con;
    }

    // Is called after all @FXML are injected (in this case the public defined attributes)
    public void initialize() {
        group.getChildren().add(root);
        lobbyCon.register(this);

        scrollPane.setContent(pane);
        scrollPane.setPrefViewportHeight(280);
        scrollPane.setPrefViewportWidth(630);
    }

    public void join() {
        lobbyCon.refreshLobbies();
    }

    public void hostGame() {
        lobbyCon.createLobby();
    }

    public void setLobby(int index, HashMap lobby){
        Pane newLobby = new Pane();
        newLobby.setPrefWidth(600);
        newLobby.setPrefHeight(50);
        newLobby.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 200), CornerRadii.EMPTY, Insets.EMPTY)));
        Font font = new Font(26);
        Text name = new Text((String)lobby.get("lobbyName"));
        name.setFont(font);
        name.setLayoutY(20);
        TextField password = new TextField();
        password.setLayoutX(200);
        Button button = new Button("join");
        button.setLayoutX(400);
        button.setOnAction(event -> joinLobby((String)lobby.get("lobbyId"),index, password.getText()));
        newLobby.getChildren().add(name);
        newLobby.getChildren().add(password);
        newLobby.getChildren().add(button);
        newLobby.setLayoutY(60 * index);
        pane.getChildren().add(newLobby);
    }

    private void joinLobby(String id, int index, String password) {
        lobbyCon.joinLobby(Integer.parseInt(id),index, password);
    }

    @Override
    public void update(ObservableLobby lo) {
        if(lo.getLobbyName().size() > 0)
            lobbies.getChildren().add(scrollPane);
        for(int i = 0; i < lo.getLobbyName().size(); i++){
            setLobby(i, lo.getLobbyName().get(i));
        }

    }

    public void terug(ActionEvent actionEvent) {
    }
}
