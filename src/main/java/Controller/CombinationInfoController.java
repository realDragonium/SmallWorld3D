package Controller;

import Enums.GameViewEnum;
import Model.CombinationInfoModel;
import Objects.SpecialFXMLLoader;
import View.CombinationInfoView;

import java.util.concurrent.Callable;

public class CombinationInfoController {
    private CombinationInfoModel model;
    private GameController gameCon;

    public CombinationInfoController(GameController gameCon){
        this.gameCon = gameCon;
        model = new CombinationInfoModel();
        createCombiInfoView();
    }

    public void createCombiInfoView(){
        new SpecialFXMLLoader().loader("/CombinationInfoView.fxml", (Callable<CombinationInfoView>) () -> new CombinationInfoView(this));
    }

    public void showCombinationInfo(CombinationController combi, boolean inShop){
        model.inShop = inShop;
        model.showCombination(combi);
        gameCon.addToGameView(GameViewEnum.COMBIINFO);
    }

    public void hideInfoScreen(){
        gameCon.removeFromGameView(GameViewEnum.COMBIINFO);
    }

    public void buyThisItem() {
        model.getCombi().buyItem();
    }

    public void registerObserver(CombinationInfoView obs) {
        model.register(obs);
    }
}
