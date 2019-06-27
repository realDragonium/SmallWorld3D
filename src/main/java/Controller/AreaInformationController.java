package Controller;

import Model.AreaInformationModel;
import Observer.AreaInformationObserver;
import View.AreaInformationView;

public class AreaInformationController {

    private AreaInformationModel model = new AreaInformationModel();
    private GameController gameCon;

    public AreaInformationController(GameController gameCon){
        this.gameCon = gameCon;
    }

    public void putAreaInformationScreen(AreaController area){
        model.setArea(area);
        model.setActive();
    }

    public void closeAreaInformation(){
        model.setNonActive();
    }

    public void AttackArea() {
        gameCon.getAttCon().attackArea(model.getArea());
    }

    public void registerObserver(AreaInformationObserver ob) {
        model.register(ob);
    }
}
