package Controller;

import Model.AreaInformationModel;
import Observer.AreaInformationObserver;
import Enum.GameViewEnum;

public class AreaInformationController {

    private AreaInformationModel model = new AreaInformationModel();
    private GameController gameCon;

    public AreaInformationController(GameController gameCon){
        this.gameCon = gameCon;
    }

    public void putAreaInformationScreen(AreaController area){
        model.setArea(area);
        gameCon.addToGameView(GameViewEnum.AREAINFO);
    }

    public void closeAreaInformation(){
        gameCon.removeFromGameView(GameViewEnum.AREAINFO);
    }

    public void AttackArea() {
//        gameCon.getCurrentPlayer().getActiveCombination().attackThisArea(model.getArea());
        gameCon.getFireBase().attackAction(model.getArea().getId());
    }

    public void registerObserver(AreaInformationObserver ob) {
        model.register(ob);
    }
}
