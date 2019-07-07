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
		appCon.getInLobbyCon().createLobby("host");
	}

	public void refreshLobbies(){
		fb.pushAllLobbiesUpdate(this);
	}

	public void joinLobby(int id, int index, String password){
		String name = appCon.getAccount().getAccountName();
		if (lobbymodel.getLobbyName().get(index).get("password").equals(password)) appCon.getInLobbyCon().joinLobby(name, id);
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

    public void setAsActive() {
//	    refreshLobbies();
	    appCon.setActiveView(ApplicationViewEnum.LOBBY);
    }
}
