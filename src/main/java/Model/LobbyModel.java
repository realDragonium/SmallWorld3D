package Model;

import Observable.ObservableLobby;
import Observer.LobbyObserver;

import java.util.ArrayList;
import java.util.List;

public class LobbyModel implements ObservableLobby {
	private List<LobbyObserver> observers = new ArrayList<>();
	private List<String> lobbynamen = new ArrayList<>();


	// returns a list of lobbyNames
	@Override
	public List<String> getLobbyName() {
		return lobbynamen;
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
}
