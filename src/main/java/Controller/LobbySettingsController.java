package Controller;

import Model.LobbySettingsModel;
import Observer.LobbySettingsObserver;

public class LobbySettingsController {
    private LobbySettingsModel mod = new LobbySettingsModel();


    LobbySettingsController(){

    }

    public void lobbyView(){
        new LobbyController();
    }

    public void startLobby(String lobbyNaam, int playerAmount){
        new InLobbyController(lobbyNaam, 1);
    }

    public void register(LobbySettingsObserver ob){
        mod.register(ob);
    }




}
