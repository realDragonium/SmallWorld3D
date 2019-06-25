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






















/*

	public String getLobbyNaam() {
		return lobbyNaam;
	}

	public void setPlayerAmount(){
		this.playerAmount = playerAmount;
	}


	// zet de lobby naam
	public void setLobbyName(String lobbyName){
		this.lobbyNaam = lobbyName;
	}


	// zet de aantal spelers
	public void setPlayerAmount(String playerAmount) {
		this.playerAmount = playerAmount;
	}


	public int getLobbyAmount() {
		notifyAllObservers();
		return lobbyAmount;
	}

*/


	/*
	public void hostLobby(String lobNaam) {
		lobbySize = new String[4];           // Lege array die 4 groot is.
		lobbyAmount++;  // aantal lobbies = +1
		lobbyNaam = lobNaam;
		spelerToevoegen();
		notifyAllObservers();
	}

*/

	/*
	public void exitLobby(int decreaseLobbySize) {
		lobbyAmount = decreaseLobbySize;
		lobbyAmount--;
		lobbySizeCounter--;
		notifyAllObservers();
	}
*/

/*
	public void joinGame() {
		spelerToevoegen();
	}



/*

	public void newLobbyList(List<String> namen){
		lobbynamen = namen;
		System.out.println(namen.size());
		notifyAllObservers();
	}

*/






//	public void spelerToevoegen() {
//		try {
//			if(lobbySizeCounter < 4) {
//				lobbySize[lobbySizeCounter] = spelers[lobbySizeCounter];
//				lobbySizeCounter++;
//			}
//		} catch(ArrayIndexOutOfBoundsException e){
//			}
//	}
//
//
//	public int getLobbySizeCounter() {
//		return lobbySizeCounter;
//	}
//
///

}
