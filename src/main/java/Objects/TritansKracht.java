package Objects;

import Controller.RaceController;
import Enum.TurnFase;

/** The titans kracht class implements Kracht
 *  handles the logic for the titans kracht
 *
 * @author yoran
 * @version June 2019
 *
 */

public class TritansKracht implements Kracht{

    private TurnFase phase = TurnFase.conquering;
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
        System.out.println();
        if(raceCon.getCombiCon().getPlayer().getGameCon().getAttCon().getAttackArea().isNextToWater()){
            raceCon.getCombiCon().getPlayer().getGameCon().getAttCon().removeFichesNeeded(1);
        }
    }

    @Override
    public boolean checkPhaseAction(TurnFase curPhase) {
        return curPhase.equals(phase);
    }
}
