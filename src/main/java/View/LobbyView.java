package View;

import Controller.LobbyController;
import Observable.ObservableLobby;
import Observer.LobbyObserver;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;

public class LobbyView implements LobbyObserver {
    private LobbyController lobbyCon;
    private Group group;
    public Group root;
    public Button terug, hosten;
    public GridPane panel;
    private Button activeButton;
    private int gridCounter = 0;             // startwaarde aantal aangemaakte lobbies = 0
    private GridPane grid= new GridPane();

    public LobbyView(Group group, LobbyController con) {
        this.group = group;
        this.lobbyCon = con;
    }

    // Is called after all @FXML are injected (in this case the public defined attributes)
    public void initialize() {
        group.getChildren().add(root);
        lobbyCon.register(this);
        panel.setVgap(10);
        List<String> lijst = null;   // Gets lobbynames from firebase and puts in a list

        for(String lobbyNaam: lijst) {
            Button btn = new Button(lobbyNaam);
            panel.add(btn, 0, gridCounter);
            panel.getChildren().get(gridCounter).setId("button" + gridCounter);
            gridCounter++;

            // On button clicked, the button will change red.
            btn.setOnAction(d -> {
                if (activeButton != null) {
                    activeButton.setStyle(" -fx-background-color:   -fx-background");
                }
                btn.setStyle("-fx-background-color: red;");
                activeButton = btn;
            });
        }
    }

    public void join() {
        lobbyCon.joinLobby(activeButton.getText());               // start de LobbySettingView
    }

    private void host(){
        lobbyCon.lobbyEdit();
    }

    private void addLobbyFirebase(String lobbyNaam) {
        panel.add(new Button(lobbyNaam), 0, gridCounter);
    }

    public void hostGame(ActionEvent t) {
        if (gridCounter < 5) {
            gridCounter++;
            host();
        } else {
            ((Node) t.getSource()).setOpacity(0);
            root.getChildren().remove(t.getSource());
        }
    }

    @Override
    public void update(ObservableLobby lo) {
        List<String> lobbynamen =  lo.getLobbyName();
        for(String test:lobbynamen){
            addLobbyFirebase(test);
        }
    }

    public void terug(ActionEvent actionEvent) {
    }
}
