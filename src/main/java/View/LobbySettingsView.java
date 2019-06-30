package View;

import Controller.LobbySettingsController;
import Observer.LobbySettingsObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class LobbySettingsView implements LobbySettingsObserver{
    public Button terug;
    private ObservableList list = FXCollections.observableArrayList("2 players", "3 players", "4 players");
    private LobbySettingsController con;
    private Group group;
    public Group root;
    public TextField lobbyNaam;
    public ChoiceBox<String> box;
    private int playerAmount;
    private String textLobby;
    public Button hosten;
    public Button test;

    //public GridPane lobbyLijst;
    //private Button[] lobbyQuantity = new Button[5];
   // private int gridCounter = 0;

    public LobbySettingsView(Group group, LobbySettingsController con) {
        this.group = group;
        this.con = con;
    }

    public void submit(){
        textLobby = lobbyNaam.getText();
        playerAmount = Integer.valueOf(box.getValue().split(" ")[0]);
       // createLobby();
        con.startLobby(textLobby, playerAmount);
        // con.lobbyView(textLobby);   // gaat terug naar lobby overzicht
    }

    public void back(){
        con.lobbyView();
    }

    private void playerAmountBox(){
        box.setItems(list);
        box.setValue("Select Lobby Size");
    }

    public void initialize() {
        group.getChildren().add(root);
        con.register(this);
        playerAmountBox();
    }

    public void lobbyName(){}

    @Override
    public void update() {
    }

}
