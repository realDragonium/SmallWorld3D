package Controller;

import Applicatie.Applicatie;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import Model.LoginModel;
import Observer.LoginObserver;

public class LoginController {
    private Applicatie app = SceneManager.getInstance().getApp();
	private FirebaseServiceOwn fb = app.getFirebaseService();
    private LoginModel loginModel = new LoginModel();

    public LoginController(){
        SceneManager.getInstance().createLoginView(this);
    }
    
    public void validateLoginInfo(String username, String password) {
    	if(fb.login(username, password)) {
            loginModel.loginAccepted(true);
            app.setAccount(new AccountController(username));
    	} else loginModel.setFailedAttempt();
    }

    public void register(String username, String password) {
        if (fb.register(username, password)) {
            loginModel.loginAccepted(true);
            app.setAccount(new AccountController(username));
        }
        else loginModel.setFailedAttempt();
    }
    
    public void register(LoginObserver lo) {
    	loginModel.register(lo);
    }

	public void goToHomeScreen() {
		new HomeScreenController();
	}
}