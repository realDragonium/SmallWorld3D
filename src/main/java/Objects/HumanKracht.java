package Objects;

import Controller.AreaController;
import Controller.RaceController;
import Enum.AreaType;


public class HumanKracht implements Kracht{

    private RaceController raceCon;

    @Override
    public void setRaceCon(RaceController raceCon) {
        this.raceCon = raceCon;
    }

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
        for(AreaController area : raceCon.getAllAreas()){
            if(area.getAreaType().equals(AreaType.farm)){
                raceCon.getCombiCon().getPlayer().addPoints(1);
            }
        }
    }
}
