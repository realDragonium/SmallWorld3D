package Controller;

import Enum.TurnFase;
import Model.RaceModel;
import Objects.Kracht;
import Objects.RaceFiche;

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
		updatePlayerFicheAmount();
		return tempFiches;
	}

	void pushFiches(Stack<RaceFiche> fiches){
		model.pushFiches(fiches);
		updatePlayerFicheAmount();
	}

	boolean hasEnoughFiches(int count){
		return count <= model.getFichesCount();
	}

	void fichesOver(){
	}

	void doKractAction(){
		kracht.doAction();
	}

    void returnFiches() {
		for(AreaController area : model.getAreas()){
			area.returnAllButOne(this);
		}
		updatePlayerFicheAmount();
    }

	void destroyAllFichesButOne(){
		model.removeAllFichesButOne();
		updatePlayerFicheAmount();
	}

    void addArea(AreaController area){
		model.addArea(area);
	}

	private void updatePlayerFicheAmount(){
		combiCon.getPlayer().setFiches(model.getFichesCount());
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

	boolean checkPhaseActoin(TurnFase curPhase) {
		return kracht.checkPhaseAction(curPhase);
	}

    public CombinationController getCombiCon() {
		return combiCon;
    }

    void addFiche(RaceFiche oneFiche) {
		model.addFiche(oneFiche);
		updatePlayerFicheAmount();
    }

	RaceFiche removeFiche() {
		RaceFiche tempFiche = model.getFiche();
		updatePlayerFicheAmount();
		return tempFiche;

	}
}
