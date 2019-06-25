package Controller;

import Applicatie.Applicatie;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.LobbySettingsModel;
import Observer.LobbySettingsObserver;

public class LobbySettingsController {
    private Applicatie app = SceneManager.getInstance().getApp();
    private FirebaseServiceOwn fb = app.getFirebaseService();
    private LobbySettingsModel mod = new LobbySettingsModel();


    LobbySettingsController(){
        SceneManager.getInstance().createLobbySettingsView(this);
    }

    public void lobbyView(){
        new LobbyController();
    }

    public void startLobby(String lobbyNaam, int playerAmount){
        fb.createLobby(playerAmount, lobbyNaam, app.getAccountCon().getAccountName());
        new InLobbyController(lobbyNaam, 1);
    }

    public void register(LobbySettingsObserver ob){
        mod.register(ob);
    }




}
