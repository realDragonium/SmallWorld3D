package Special.RedeployPhase;

import Controller.CombinationController;
import Controller.FicheController;
import Controller.GameController;
import Special.SpecialAction;

public class SkeletonAction implements  RedeployPhase, SpecialAction {

    @Override
    public void doAction(GameController gameCon, CombinationController combi) {
        int numberOfFiches = (int) Math.floor(combi.getNumberThisRoundConqueredAreas()/2);
        for (int i = 0; i < numberOfFiches ; i++) {
            FicheController ficheCon = new FicheController(1, combi.getRaceName());
            combi.addRaceFiche(ficheCon);
        }
    }
}
