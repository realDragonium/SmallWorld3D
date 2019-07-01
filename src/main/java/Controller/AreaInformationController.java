package Controller;

import Model.AreaInformationModel;
import Observer.AreaInformationObserver;
import Enum.GameViewEnum;

public class AreaInformationController {

    private AreaInformationModel model = new AreaInformationModel();
    private GameController gameCon;
    private FirebaseGameController fbGame;

    public AreaInformationController(GameController gameCon){
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
    }

    public void putAreaInformationScreen(AreaController area){
        model.setArea(area);
        gameCon.addToGameView(GameViewEnum.AREAINFO);
    }

    public void closeAreaInformation(){
        gameCon.removeFromGameView(GameViewEnum.AREAINFO);
    }

    public void AttackArea() {
        fbGame.attackAction(model.getArea().getId());
    }

    public void registerObserver(AreaInformationObserver ob) {
        model.register(ob);
    }

    public void addFiche() {
        fbGame.addsFicheAction(model.getArea().getId());
    }

    public void removeFiche() {
        fbGame.removeFicheAction(model.getArea().getId());
    }

    public void leaveArea() {
        fbGame.leavesFicheAction(model.getArea().getId());
    }
}
