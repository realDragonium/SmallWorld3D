package Controller;

import Managers.SceneManager;

/**
 *
 * @author : Martijn van der Steen
 * @version : Juni 2019
 */
public class VervallenController {

    GameController gameCon;

    VervallenController(GameController gameCon){
        this.gameCon = gameCon;
//        SceneManager.getInstance().loadVerval(this);
    }

    /**
     * De methode inVerval() wordt aangeroepen de methode inVerval() uit de VervallenView class.
     * De methode inVerval() roept 3 methodes in dezelfde class aan.
     */

    public void inVerval() {
        makeFicheToOne();
        makeCombinationNonActive();
        //eindig zijn beurt
    }

    /**
     * De methode makeFicheToOne() wordt aangeroepen door de methode inVerval().
     * De methode makeFicheToOne() zorgt ervoor dat er één fiche komt te staan op het active ras van de current player.
     *
     */

    //methode maakt in de Areacontroller fiches naar 1 per gebied (empty maar nog niet 1 per gebied)
    private void makeFicheToOne() {
        gameCon.getCurrentPlayer().getActiveCombination().destroyAllFichesButOne();
    }

    /**
     * De methode makeFicheToOne() wordt aangeroepen door de methode inVerval().
     * De methode makeFicheToOne() zorgt ervoor dat het activeras van de currentplayer non-active wordt gemaakt.
     */
    //methode maakt in de Racemodel racefiche !raceactive. (
    private void makeCombinationNonActive(){
        gameCon.getCurrentPlayer().getActiveCombination().setToNonActive();
    }


}
