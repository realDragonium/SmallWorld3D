package Controller;

import Managers.SceneManager;
import Model.LobbyModel;
import Observer.LobbyObserver;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LobbyController {
	private LobbyModel lobbymodel = new LobbyModel();



	public LobbyController(){
//		SceneManager.getInstance().createLobbyView(this);
	}


	public void joinLobby(String Name){
		int id = 0;
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


}
