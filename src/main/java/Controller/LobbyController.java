package Controller;

import Firebase.FirebaseAllLobbiesObserver;
import Firebase.FirebaseLobbyObserver;
import Firebase.FirebaseLobbyService;
import Model.LobbyModel;
import Observer.LobbyObserver;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;

public class LobbyController implements FirebaseAllLobbiesObserver {
	private LobbyModel lobbymodel = new LobbyModel();
	private FirebaseLobbyService fb;
	private ApplicationController appCon;

	public LobbyController(ApplicationController appCon){
		this.fb = appCon.getLobbyFireBase();

		this.appCon = appCon;
	}

	public void createLobby(){
		appCon.getInLobbyCon().createLobby("host");
	}

	public void refreshLobbies(){
		fb.pushAllLobbiesUpdate(this);
	}

	public void joinLobby(int id, int index, String password){
		if (lobbymodel.getLobbyName().get(index).get("password").equals(password)) appCon.getInLobbyCon().joinLobby("player", id);
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
}
