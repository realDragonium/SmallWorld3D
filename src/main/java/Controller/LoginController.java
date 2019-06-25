package Controller;


import Enum.ApplicatieViewEnum;

public class LoginController {
    private ApplicatieController appCon;
    LoginController(ApplicatieController appCon){
        this.appCon = appCon;
    }

    public void goToHomeScreen() {
        appCon.setActiveView(ApplicatieViewEnum.HOMESCREEN);
    }
}
