package Objects;

import Controller.AreaController;
import Controller.RaceController;
import Enums.AreaProperty;


public class WizzardsKracht implements Kracht{
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
            if(area.getSpecialProp().equals(AreaProperty.Magical)){
                raceCon.getCombiCon().getPlayer().addPoints(1);
            }
        }
    }
}
