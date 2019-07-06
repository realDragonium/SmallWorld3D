package Controller;

import Model.AccountModel;

public class AccountController {

    AccountModel model;

    AccountController(String username) {
        model = new AccountModel(username);
    }

    String getAccountName(){
        return model.getAccountName();
    }
}
