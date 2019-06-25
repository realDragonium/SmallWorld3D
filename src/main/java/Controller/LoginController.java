package Controller;

import Enum.ApplicatieViewEnum;
import Model.LoginModel;
import Observer.LoginObserver;

public class LoginController {
    private LoginModel loginModel = new LoginModel();
    private ApplicationController appCon;

    public LoginController(ApplicationController applicationController) {
        appCon = applicationController;
    }

//    public void validateLoginInfo(String username, String password) {
//    	if(fb.login(username, password)) {
//            loginModel.loginAccepted(true);
//            app.setAccount(new AccountController(username));
//    	} else loginModel.setFailedAttempt();
//    }
//
//    public void register(String username, String password) {
//        if (fb.register(username, password)) {
//            loginModel.loginAccepted(true);
//            app.setAccount(new AccountController(username));
//        }
//        else loginModel.setFailedAttempt();
//    }
    
    public void register(LoginObserver lo) {
    	loginModel.register(lo);
    }

	public void goToHomeScreen() {
		appCon.setActiveView(ApplicatieViewEnum.HOMESCREEN);
	}
}