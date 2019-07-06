package Controller;


import Firebase.FirebaseLoginService;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.HashMap;

public class FirebaseLoginController {

    private FirebaseLoginService service;

    public void setService(FirebaseLoginService service) {
        this.service = service;
    }

    public boolean login(String username, String password) {
        if (service.exists(username)) {
            DocumentSnapshot docSnap = service.getPassword(username);
            if (docSnap.getString("password").equals(password) &&
                !docSnap.getBoolean("loggedIn")) {
                login(username);
                return true;
            }
        }
        return false;
    }

    private void login(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("loggedIn", true);
        service.setLoggedIn(username, map);
    }

    void logout(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("loggedIn", false);
        service.setLoggedIn(username, map);
    }

    public boolean register(String username, String password) {
        System.out.println("registeer");
        if (service.exists(username)) return false;
        HashMap<String, Object> map = new HashMap<>();
        map.put("password", password);
        map.put("loggedIn", true);
        service.register(username, map);
        return true;

    }
}
