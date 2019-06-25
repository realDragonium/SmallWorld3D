package Controller;

import Enum.ApplicatieViewEnum;
import Model.HomeScreenModel;
import Observer.HomeScreenObserver;

public class HomeScreenController {
	
    private HomeScreenModel hsModel = new HomeScreenModel();
    private ApplicatieController appCon;

    public HomeScreenController(ApplicatieController applicatieController) {
        appCon = applicatieController;
    }

    public void register(HomeScreenObserver mvo) {
    	hsModel.register(mvo);
    }

    public void startGame() {
        appCon.setActiveView(ApplicatieViewEnum.GAME);
    }
}
