package Controller;

import Enum.ApplicatieViewEnum;
import Model.HomeScreenModel;
import Observer.HomeScreenObserver;

public class HomeScreenController {
	
    private HomeScreenModel hsModel = new HomeScreenModel();
    private ApplicationController appCon;

    public HomeScreenController(ApplicationController applicationController) {
        appCon = applicationController;
    }

    public void register(HomeScreenObserver mvo) {
    	hsModel.register(mvo);
    }

    public void startGame() {
        appCon.setActiveView(ApplicatieViewEnum.GAME);
    }
}
