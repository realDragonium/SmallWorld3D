package Controller;

import Applicatie.Applicatie;
import Managers.SceneManager;
import Model.LobbyModel;
import Observer.LobbyObserver;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LobbyController {
	private LobbyModel lobbymodel = new LobbyModel();



	public LobbyController(){
		SceneManager.getInstance().createLobbyView(this);
	}


	public void joinLobby(String Name){
		Applicatie app = SceneManager.getInstance().getApp();
		int id = app.getFirebaseService().joinLobby(Name, app.getAccountCon().getAccountName());
		if(id>0){
			new InLobbyController(Name, id);
		}
	}

	public void register(LobbyObserver ob) {
		lobbymodel.register(ob);
	}


	// Creates a new LobbySettingsController which gets its corresponding view in the SceneManager, by giving itself along
	public void lobbyEdit(){
		new LobbySettingsController();
	}


	// Tries to get a list of available lobbies.
	public List<String> getFirebaseLobbyNamen(){
		try {
			return SceneManager.getInstance().getApp().getFirebaseService().getActiveLobbies();
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
