package Controller;

import Enums.ApplicationViewEnum;
import Model.LoginModel;
import Observer.LoginObserver;

public class LoginController {
    private LoginModel loginModel = new LoginModel();
    private ApplicationController appCon;
    private FirebaseLoginController fbLogin;

    public LoginController(ApplicationController applicationController) {
        appCon = applicationController;
        fbLogin = appCon.fbService.getfbLogin();
    }

    public void validateLoginInfo(String username, String password) {
    	if(fbLogin.login(username, password)) {
            loginModel.loginAccepted(true);
            appCon.setAccount(new AccountController(username));
    	} else loginModel.setFailedAttempt();
    }

    public void register(String username, String password) {
        if (fbLogin.register(username, password)) {
            loginModel.loginAccepted(true);
            appCon.setAccount(new AccountController(username));
        }
        else loginModel.setFailedAttempt();
    }
    
    public void register(LoginObserver lo) {
    	loginModel.register(lo);
    }

	public void goToHomeScreen() {
		appCon.setActiveView(ApplicationViewEnum.HOMESCREEN);
	}
}