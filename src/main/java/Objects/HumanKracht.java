package Objects;

import Controller.AreaController;
import Controller.RaceController;
import Enum.AreaType;
import Enum.TurnFase;

/** this handles the logic for the human kracht
 *
 * @author yoran
 * @version June 2019
 */

public class HumanKracht implements Kracht{

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
            if(area.getAreaType().equals(AreaType.farm)){
                raceCon.getCombiCon().gatPlayer().addPoints(1);
            }
        }
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return curPhase.equals(phase);
    }
}
