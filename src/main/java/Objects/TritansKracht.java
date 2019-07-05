package Objects;

import Controller.RaceController;


public class TritansKracht implements Kracht{
    private RaceController raceCon;

    @Override
    public void setRaceCon(RaceController raceCon){
        this.raceCon = raceCon;
    }

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
//        if(raceCon.getCombiCon().getCombi().getGameCon().getAttCon().getAttackArea().isNextToWater()){
//            raceCon.getCombiCon().getCombi().getGameCon().getAttCon().removeFichesNeeded(1);
//        }
    }

}
