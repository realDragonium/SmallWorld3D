package Objects;

import Controller.AreaController;
import Controller.RaceController;
import Enum.AreaProperty;
import Enum.TurnFase;

/** This handles the logic for the wizzard kracht
 *
 * @author yoran
 * @version June 2019
 *
 */

public class WizzardsKracht implements Kracht{

    private TurnFase phase = TurnFase.redeploying;
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
            if(area.getSpecialProp().equals(AreaProperty.magicsource)){
                raceCon.getCombiCon().gatPlayer().addPoints(1);
            }
        }
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return curPhase.equals(phase);
    }
}
