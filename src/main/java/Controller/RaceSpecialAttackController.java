package Controller;

import Enums.GameViewEnum;
import Objects.SpecialFXMLLoader;
import Special.AttackPhase.AttackPhase;
import Special.SpecialAction;
import View.PowerSpecialAttackView;
import View.RaceSpecialAttackView;

import java.util.concurrent.Callable;

public class RaceSpecialAttackController {

    private GameController gameCon;
    private SpecialAction action;


    public RaceSpecialAttackController(GameController gameCon) {
        this.gameCon = gameCon;
        createView();
    }

    public void setAction(SpecialAction action){
        this.action =action;
}

    private void createView() {
        new SpecialFXMLLoader().loader("/RaceSpecialAttackView.fxml", (Callable<RaceSpecialAttackView>) () -> new RaceSpecialAttackView(this));
    }

    public void AttackArea(String areaId) {
        final AreaController area = gameCon.getMapCon().getAreaCon(areaId);
        ((AttackPhase)action).doAction(gameCon, area);
        gameCon.removeFromGameView(GameViewEnum.AREAINFO);
    }

}
