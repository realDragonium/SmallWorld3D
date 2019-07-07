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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        scrollPane.setStyle("-fx-background:rgb(76,76,76);");
        scrollPane.setContent(pane);
        scrollPane.setPrefViewportHeight(450);
        scrollPane.setPrefViewportWidth(1180);
        lobbies.getChildren().add(scrollPane);
    }

    public void join() {
        lobbyCon.refreshLobbies();
    }

    public void hostGame() {
        lobbyCon.createLobby();
    }

    public void setLobby(int index, HashMap lobby){

        Pane newLobby = new Pane();
        ImageView ui = new ImageView();
        ui.setImage(new Image("/Lobbies/LobbyItemUI" + (index%2 + 1) + ".png"));
        ui.setFitHeight(80);
        ui.setFitWidth(1180);
        newLobby.getChildren().add(ui);
        newLobby.setPrefWidth(1180);
        newLobby.setPrefHeight(80);

        Font font = new Font("Algerian", 28);
        Text name = new Text((String)lobby.get("lobbyName"));
        Text lobbyState = new Text();
        int size = 0;
        for(int i = 1; i <= 4; i++){
            if(!((HashMap)lobby.get("playerNames")).get("player" + i).equals("")) size++;
        }

        Text players = new Text(size + "/4");
        if((Boolean)lobby.get("inProgress"))lobbyState.setText("in-Progress");
        else lobbyState.setText("in-Lobby");

        lobbyState.setLayoutX(560);
        lobbyState.setLayoutY(50);
        lobbyState.setFill(Color.WHITE);
        lobbyState.setFont(font);
        name.setFont(font);
        name.setFill(Color.WHITE);
        name.setLayoutY(50);
        name.setLayoutX(60);
        players.setFont(font);
        players.setFill(Color.WHITE);
        players.setLayoutX(860);
        players.setLayoutY(50);
        Button button = new Button("join");
        button.setLayoutX(1050);
        button.setLayoutY(20);
        button.setOnAction(event -> joinLobby((String)lobby.get("lobbyId"),index, ""));
        newLobby.getChildren().add(name);
        newLobby.getChildren().add(button);
        newLobby.getChildren().add(lobbyState);
        newLobby.getChildren().add(players);
        newLobby.setLayoutY(80 * index);
        pane.getChildren().add(newLobby);
    }

    private void joinLobby(String id, int index, String password) {
        lobbyCon.joinLobby(Integer.parseInt(id),index, password);
    }

    @Override
    public void update(ObservableLobby lo) {
        for(int i = 0; i < lo.getLobbyName().size(); i++){
            setLobby(i, lo.getLobbyName().get(i));
        }

    }

    public void terug(ActionEvent actionEvent) {
        lobbyCon.back();
    }
}
