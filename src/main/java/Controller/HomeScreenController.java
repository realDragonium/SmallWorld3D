package Controller;

import Enums.ApplicationViewEnum;
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

    public void goToLobby() {
//        appCon.setActiveView(ApplicationViewEnum.LOBBY);
        appCon.getLobbyCon().setAsActive();
    }

    public void readGuide(){
        appCon.createLeaderBordTest();
    }

    public void logout() {
        appCon.logout();
        appCon.setActiveView(ApplicationViewEnum.LOGIN);
    }
}
