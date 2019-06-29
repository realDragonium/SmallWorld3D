package Controller;

import Model.LobbyModel;
import Observer.LobbyObserver;

public class LobbyController {
	private LobbyModel lobbymodel = new LobbyModel();



	public LobbyController(){

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


	// Creates a new LobbySettingsController which gets its corresponding view in the , by giving itself along
	public void lobbyEdit(){
		new LobbySettingsController();
	}


}
