package Controller;

import Enums.GameViewEnum;
import Objects.SpecialFXMLLoader;
import Special.SpecialAction;
import View.PowerSpecialAttackView;

import java.util.concurrent.Callable;

public class PowerSpecialAttackController {

    private GameController gameCon;
    private SpecialAction action;


    public PowerSpecialAttackController(GameController gameCon) {
        this.gameCon = gameCon;
        createView();
    }

    public void setAction(SpecialAction action){
        this.action =action;
}

    private void createView() {
        new SpecialFXMLLoader().loader("/PowerSpecialAttackView.fxml", (Callable<PowerSpecialAttackView>) () -> new PowerSpecialAttackView(this));
    }

    public void AttackArea(String areaId) {
        final AreaController area = gameCon.getMapCon().getAreaCon(areaId);
        action.doAction(gameCon, area);
        gameCon.removeFromGameView(GameViewEnum.AREAINFO);
    }

}
