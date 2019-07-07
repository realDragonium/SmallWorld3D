package Controller;

import Enums.ApplicationViewEnum;
import Firebase.FirebaseLobbyObserver;
import Model.InLobbyModel;
import Observer.InLobbyObserver;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.application.Platform;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class InLobbyController implements FirebaseLobbyObserver { ;
    private InLobbyModel model = new InLobbyModel();
    private FirebaseLobbyController fb;
    private ApplicationController appCon;
    private boolean clientStart = false;

    public InLobbyController(ApplicationController appCon){
        this.appCon = appCon;
        fb = appCon.getLobbyFireBase();
    }

    public void putPlayerAtEmptySpot(String name){
        for(int i = 1; i <= 4; i++){
            if(model.getPlayer(i).equals("")){
                model.removeAsHost();
                model.setPlayer(i, name);
                model.setMyPlayer("player" + i);
                model.setMyName(name);
                if(i == 1) model.setAsHost();

                break;
            }
        }
    }

    public void setReady(){
        model.getPlayerStates().put(model.getMyPlayer(), true);
        updateLobbyInfo();
        model.notifyAllObservers();
    }


    public void joinLobby(String player, int id){

        appCon.setActiveView(ApplicationViewEnum.INLOBBY);
        model.setLobbyId(id);
        fb.joinLobby(Integer.toString(id));
        fb.actionDocumentListener(this);
        fb.pushDocumentUpdate(this);
        putPlayerAtEmptySpot(player);

        updateLobbyInfo();

    }

    public void createLobby(String player){
        int lobbyId = fb.createLobby();
        fb.actionDocumentListener(this);

        //
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->  initLobby(player, lobbyId));
            }
        };
        new Timer().schedule(task, 4000);

    }

    public void initLobby(String player, int lobbyId){
        appCon.setActiveView(ApplicationViewEnum.INLOBBY);
        fb.pushDocumentUpdate(this);
        model.setPlayer(1, player);
        model.setLobbyNaam("lobby" + lobbyId);
        model.setMyName(player);
        model.setMyPlayer("player1");
        model.setLobbyId(lobbyId);
        model.setAsHost();
        updateLobbyInfo();
        model.notifyAllObservers();

    }

    public void changeLobbyName(String name){
        model.setLobbyNaam(name);
        updateLobbyInfo();
    }

    public HashMap getGameInfo(){
        HashMap<String, Object> info = new HashMap<>();
        info.put("playerNames", model.getPlayerNames());
        info.put("playerStates", model.getPlayerStates());
        info.put("lobbyId", Integer.toString(model.getLobbyId()));
        info.put("password", model.getPassword());
        info.put("lobbyName", model.getLobbyNaam());
        info.put("gameSpeed", model.getGameSpeed());
        info.put("inProgress", model.getStart());
        return info;
    }

    public void updateLobbyInfo(){
        fb.changeLobbyInfo(getGameInfo());
    }

    public void start(){            // start button
        boolean allPlayersReady = true;
        for(int i = 1; i <= 4; i++){
            if(!model.getPlayer(i).equals("")) {
                if (!model.getPlayerState(i)) {
                    allPlayersReady = false;
                }
            }
        }
        if(allPlayersReady) {
            startGame();
            clientStart = true;
            model.startGame(true);
            updateLobbyInfo();
        }
    }

    public void startGame(){
        appCon.startGame(getGameInfo());
    }


    public void exitLobby(){
        model.reset();
        fb.leaveLobby(model.getMyPlayer());
        appCon.getLobbyCon().setAsActive();
    }

    public void register(InLobbyObserver ob){
        model.register(ob);
    }

    public void unregister(InLobbyObserver ob){
        model.unregister(ob);
    }

    @Override
    public void update(DocumentSnapshot ds) {
        Map<String, Object> info = ds.getData();
        model.setLobbyNaam((String)info.get("lobbyName"));
        model.setPassword((String)info.get("password"));
        model.startGame((Boolean)info.get("inProgress"));
        if((Boolean)info.get("inProgress") && !clientStart){
            clientStart = true;
            Platform.runLater(()-> startGame());
        }
        Map<String, String> players = (Map)info.get("playerNames");
        Map<String, Boolean> states = (Map)info.get("playerStates");
        for(int i = 1; i <= 4; i++){
            model.setPlayer(i, players.get("player"+i));
            model.setPlayerReady(i, states.get("player"+i));
        }
        Platform.runLater(()-> model.notifyAllObservers());

    }

    public void changeLobbyPassword(String text) {
        model.setPassword(text);
        updateLobbyInfo();
    }

    public void changeGameSpeed(String value) {
        model.setGameSpeed(value);
    }

    public void joinInProgress(String name, int id) {
        System.out.println("joining game in progress!");
        clientStart = true;
        model.setLobbyId(id);
        model.setMyName(name);
        fb.joinLobby(Integer.toString(id));
        fb.actionDocumentListener(this);
        fb.pushDocumentUpdate(this);
        for(int i = 1; i <= 4; i++){
            if(model.getPlayer(i).equals(name)){
                model.setMyPlayer("player" + i);
            }
        }
        startGame();
    }
}
