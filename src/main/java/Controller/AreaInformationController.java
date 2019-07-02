package Controller;

import Model.AreaInformationModel;
import Objects.SpecialFXMLLoader;
import Observer.AreaInformationObserver;
import Enums.GameViewEnum;
import View.AreaInformationView;

import java.util.concurrent.Callable;

public class AreaInformationController {

    private AreaInformationModel model = new AreaInformationModel();
    private GameController gameCon;
    private FirebaseGameController fbGame;

    public AreaInformationController(GameController gameCon){
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
        createAreaInfoView();
    }

    private void createAreaInfoView() {
        new SpecialFXMLLoader().loader("/AreaInfoView.fxml", (Callable<AreaInformationView>) () -> new AreaInformationView(this));
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
        closeAreaInformation();
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
        closeAreaInformation();
    }
}
