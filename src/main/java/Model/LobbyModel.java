package Model;

import Observable.ObservableLobby;
import Observer.LobbyObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyModel implements ObservableLobby {
	private List<LobbyObserver> observers = new ArrayList<>();
	private List<HashMap> lobbies = new ArrayList<>();


	// returns a list of lobbyNames
	@Override
	public List<HashMap> getLobbyName() {
		return lobbies;
	}

	// adds an observers to the observer list
	@Override
	public void register(LobbyObserver ob) {
		observers.add(ob);
	}

	// removes an observer from the list of observers
	@Override
	public void unregister(LobbyObserver ob) {
		observers.remove(ob);
	}

	// Every observer from list of observers, gets updated by giving itself along
	@Override
	public void notifyAllObservers() {
		for(LobbyObserver obs : observers) {
			obs.update(this);
		}
	}

	public void clearLobbiesList(){
		lobbies.clear();
	}

	public void setLobby(HashMap lobby, String id) {
		lobby.put("lobbyId", id);
		lobbies.add(lobby);
	}
}
