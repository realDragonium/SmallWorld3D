package Controller;

import Enums.GameViewEnum;
import Model.CombinationInfoModel;
import Objects.SpecialFXMLLoader;
import View.CombinationInfoView;
import View.GameView;

import java.util.concurrent.Callable;

public class CombinationInfoController {
    private CombinationInfoModel model = new CombinationInfoModel();
    private GameController gameCon;

    public CombinationInfoController(GameController gameCon){
        this.gameCon = gameCon;
        createCombiInfoView();
    }

    public void createCombiInfoView(){
        new SpecialFXMLLoader().loader("/CombinationInfoView.fxml", (Callable<CombinationInfoView>) () -> new CombinationInfoView(this));
    }

    public void showCombinationInfo(CombinationController combi){
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
