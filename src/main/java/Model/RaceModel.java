package Model;

import Controller.AreaController;
import Objects.RaceFiche;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class RaceModel {

	private String raceId;
	private Stack<RaceFiche> availableFiches = new Stack<>();
	private List<AreaController> areas = new ArrayList<>();
	
	public RaceModel(String id, int fichesAmount) {
		createFiches(fichesAmount);
		this.raceId = id;
	}

	private void createFiches(int amount){
		IntStream.range(0,amount).forEach(i -> availableFiches.push(new RaceFiche()));
	}

	public RaceFiche getFiche(){
		return availableFiches.pop();
	}

	public Stack<RaceFiche> getFiches(int count){
		Stack<RaceFiche> leaveFiches = new Stack<>();
		IntStream.range(0, count).forEach(i -> leaveFiches.push(availableFiches.pop()));
		return leaveFiches;
	}

	public void pushFiches(Stack<RaceFiche> fiches){
		availableFiches.addAll(fiches);
	}

	public List<AreaController> getAreas(){
		return areas;
	}

	public int getFichesCount(){
		return availableFiches.size();
	}


	public void addArea(AreaController area) {
		areas.add(area);
	}

	public Stack<RaceFiche> removeFiches(int count) {
		Stack<RaceFiche> tempFiches = new Stack<>();
		for(int i = 0; i < count; i++){
			tempFiches.add(availableFiches.pop());
		}
		return tempFiches;
	}

	public String getId(){
		return raceId;
	}

	public void removeArea(AreaController area) {
		areas.remove(area);
	}

    public void removeAllFichesButOne() {
		for(AreaController area : areas){
			area.destroyAllButOne();
		}
		availableFiches = new Stack<>();
    }

    public void addFiche(RaceFiche oneFiche) {
		availableFiches.add(oneFiche);
    }
}
