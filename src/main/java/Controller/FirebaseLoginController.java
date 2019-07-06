package Controller;


import Firebase.FirebaseLoginService;

import java.util.HashMap;

public class FirebaseLoginController {

    private FirebaseLoginService service;

    public void setService(FirebaseLoginService service) {
        this.service = service;
    }

    public boolean login(String username, String password) {
        if (service.exists(username)) {
            String tempPassword = service.getPassword(username);
            if (tempPassword.equals(password)) return true;
        }
        return false;
    }

    public boolean register(String username, String password) {
        if (service.exists(username)) return false;

        HashMap<String, Object> map = new HashMap<>();
        map.put("password", password);
        service.register(username, map);
        return true;

    }

}
