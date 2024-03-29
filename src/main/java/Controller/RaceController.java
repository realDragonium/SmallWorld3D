package Controller;

import Fiches.RaceFiche;
import Model.RaceModel;
import Objects.Kracht;

import java.util.List;
import java.util.Stack;

public class RaceController {

	private CombinationController combiCon;
	private Kracht kracht;
	private RaceModel model;

	RaceController(Kracht kracht, String id, int ficheAmount) {
		this.kracht = kracht;
		model = new RaceModel(id, ficheAmount);
		kracht.setRaceCon(this);
	}

	public List<AreaController> getAllAreas(){
		return model.getAreas();
	}

	int fichesCount(){
		return model.getFichesCount();
	}

	void setCombiCon(CombinationController combiCon){
		this.combiCon = combiCon;
	}

	Stack<RaceFiche> getFiches(int count){
		Stack<RaceFiche> tempFiches = model.removeFiches(count);
		return tempFiches;
	}

	void pushFiches(Stack<RaceFiche> fiches){
		model.pushFiches(fiches);
	}

	boolean hasEnoughFiches(int count){
		return count <= model.getFichesCount();
	}

	void fichesOver(){
	}

	void doKractAction(){
		kracht.doAction();
	}

    public void addArea(AreaController area){
		model.addArea(area);
	}

	void removeArea(AreaController area) {
		model.removeArea(area);
	}

	int getAreasAmount() {
		return model.getAreas().size();
	}

	public String getId(){
		return model.getId();
	}


    public CombinationController getCombiCon() {
		return combiCon;
    }

    void addFiche(RaceFiche oneFiche) {
		model.addFiche(oneFiche);
    }

	RaceFiche removeFiche() {
		RaceFiche tempFiche = model.getFiche();
		return tempFiche;

	}
}
