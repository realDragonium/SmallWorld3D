package View;

import Controller.InLobbyController;
import Observable.InLobbyObservable;
import Observer.InLobbyObserver;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InLobbyView implements InLobbyObserver {

    private InLobbyController con;
    private Group group;
    public Pane pane;
    public Group lobbySettings;
    public Group player1, player2, player3, player4;
    public TextField lobbyName, lobbyPassword;
    public Button start;
    public ChoiceBox gameSpeed;
    public Text name;


    public InLobbyView(InLobbyController con, Group group) {
        this.group = group;
        this.con = con;
    }

    public void initialize() {
        group.getChildren().add(pane);
        con.register(this);
    }

    public void changeLobbyName(){
        con.changeLobbyName(lobbyName.getText());
    }

    public void changeLobbyPassword(){
        con.changeLobbyPassword(lobbyPassword.getText());
    }

    public void changeLobbyGameSpeed(){
        con.changeGameSpeed(gameSpeed.getValue().toString());
    }


    // starten van het spel
    public void start(){
        con.start();
    }

//    public void joinen(){
//      //  spelerToevoegen();
//    }

    public void readyUp(){
        con.setReady();
    }

    public void exit(){
        con.exitLobby();
    }

    public void setPlayerInformation(Group group, Boolean ready, Boolean host, String name){
        group.getChildren().clear();
        Font font = new Font("Algerian" ,42);
        Text playerName = new Text(name);
        playerName.setFill(Color.WHITE);
        playerName.setLayoutX(240);
        playerName.setLayoutY(50);
        playerName.setFont(font);
        ImageView isReady = new ImageView();
        ImageView isHost = new ImageView();
        isHost.setImage(new Image("/Lobbies/HostIcon.png"));
        isHost.setFitWidth(80);
        isHost.setFitHeight(80);
        isHost.setLayoutX(100);
        isHost.setVisible(host);
        isReady.setImage(new Image("/Lobbies/ReadyIcon.png"));
        isReady.setVisible(ready);
        isReady.setLayoutX(1060);
        group.getChildren().add(isHost);
        group.getChildren().add(playerName);
        group.getChildren().add(isReady);
    }

    @Override
    public void update(InLobbyObservable ilo){
        resetPlayerInformation();
        if(!ilo.getPlayer(1).equals("")){
            setPlayerInformation(player1, ilo.getPlayerState(1), true, ilo.getPlayer(1));
        }
        if(!ilo.getPlayer(2).equals("")){
            setPlayerInformation(player2, ilo.getPlayerState(2), false, ilo.getPlayer(2));
        }
        if(!ilo.getPlayer(3).equals("")){
            setPlayerInformation(player3, ilo.getPlayerState(3), false, ilo.getPlayer(3));
        }
        if(!ilo.getPlayer(4).equals("")){
            setPlayerInformation(player4, ilo.getPlayerState(4), false, ilo.getPlayer(4));
        }
        name.setText(ilo.getLobbyName());
        start.setVisible(ilo.isHost());
        showLobbySettings(ilo.isHost());
    }

    private void resetPlayerInformation() {
        player1.getChildren().clear();
        player2.getChildren().clear();
        player3.getChildren().clear();
        player4.getChildren().clear();
    }

    private void showLobbySettings(boolean host) {
        lobbySettings.setVisible(host);
    }


    public void exitLobby() {
        con.exitLobby();
    }
}
