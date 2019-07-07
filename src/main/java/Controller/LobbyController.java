package Controller;

import Enums.ApplicationViewEnum;
import Firebase.FirebaseAllLobbiesObserver;
import Model.LobbyModel;
import Observer.LobbyObserver;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;

public class LobbyController implements FirebaseAllLobbiesObserver {

	private LobbyModel lobbymodel = new LobbyModel();
	private FirebaseLobbyController fb;
	private ApplicationController appCon;

	public LobbyController(ApplicationController appCon){
		this.appCon = appCon;
		this.fb = appCon.getLobbyFireBase();
	}

	public void createLobby(){
		String name = appCon.getAccount().getAccountName();
		appCon.getInLobbyCon().createLobby(name);
	}

	public void refreshLobbies(){
		fb.pushAllLobbiesUpdate(this);
	}

	public void joinLobby(int id, int index, String password){
		HashMap lobby = lobbymodel.getLobbyName().get(index);
		String name = appCon.getAccount().getAccountName();
		if((Boolean)lobby.get("inProgress")){
			if(isPlaying(lobby, name)) appCon.getInLobbyCon().joinInProgress(name, id);
		}
		else if (lobbymodel.getLobbyName().get(index).get("password").equals(password)) appCon.getInLobbyCon().joinLobby(name, id);
	}

	private boolean isPlaying(HashMap lobby, String name) {
		for(int i = 1; i <= 4; i++) {
			if(((HashMap)lobby.get("playerNames")).get("player" + i).equals(name))return true;
		}
		return false;
	}

	public void register(LobbyObserver ob) {
		lobbymodel.register(ob);
	}

	@Override
	public void update(QuerySnapshot qs) {
		System.out.println("update!");
		List info = qs.getDocuments();
		lobbymodel.clearLobbiesList();
		for(Object snap : info){
			lobbymodel.setLobby((HashMap)((QueryDocumentSnapshot)snap).getData(), ((QueryDocumentSnapshot) snap).getId());
		}
		lobbymodel.notifyAllObservers();
	}

    void setAsActive() {
		refreshLobbies();
	    appCon.setActiveView(ApplicationViewEnum.LOBBY);

    }

	public void back() {
		appCon.setActiveView(ApplicationViewEnum.HOMESCREEN);
	}
}
