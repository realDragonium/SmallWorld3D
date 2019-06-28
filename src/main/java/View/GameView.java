package View;

import Controller.GameController;
import Observable.GameObservable;
import Observer.GameObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import Enum.GameViewEnum;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameView implements GameObserver {

    private Group root;
    private Group notBasicRoot = new Group();
    private GameController gameCon;
    private Map<String, Group> groups = new HashMap<>();

    @FXML
    private Group Group3d, mapGroup, buttonGroup, playerGroup, roundGroup, turnGroup, shopGroup, timerGroup,
            vervalGroup, diceGroup, redeployingGroup, infoGroup, attackGroup;
    @FXML
    private Pane headPane;


    public GameView(GameController gameCon, Group group){
        this.gameCon = gameCon;
        root = group;
    }

    public void initialize() {
        createGroups();
        createViews();
        basicViewLayout();
        gameCon.register(this);
        gameCon.setPlayerPositions();

    }

    private void createGroups(){
        groups.put("map2D", new Group());
        groups.put("map3D", new Group());
        groups.put("players", new Group());
        groups.put("round", new Group());
        groups.put("turn", new Group());
        groups.put("button", new Group());
        groups.put("shop", new Group());
        groups.put("timer", new Group());
        groups.put("verval", new Group());
        groups.put("dice", new Group());
        groups.put("redeploy", new Group());
        groups.put("info", new Group());
        groups.put("attack", new Group());
        groups.put("areaInfo", new Group());
        groups.put("uiOverlay", new Group());
    }

    private void createViews(){
        gameCon.createMap2DView(groups.get("map2D"));
        gameCon.create3dView(groups.get("map3D"));
        gameCon.createPlayerView(groups.get("players"), "player1");
        gameCon.createPlayerView(groups.get("players"), "player2");
        gameCon.createPlayerView(groups.get("players"), "player3");
        gameCon.createPlayerView(groups.get("players"), "player4");
        gameCon.createRoundView(groups.get("round"));
        gameCon.createTurnView(groups.get("turn"));
        gameCon.createButtonView(groups.get("button"));
        gameCon.createShopView(groups.get("shop"));
        gameCon.createTimerView(groups.get("timer"));
        gameCon.createVervalView(groups.get("verval"));
        gameCon.createDiceView(groups.get("dice"));
        gameCon.createRedeployView(groups.get("redeploy"));
        gameCon.createInfoView(groups.get("info"));
        gameCon.createAttackView(groups.get("attack"));
        gameCon.createAreaInfoView(groups.get("areaInfo"));
        gameCon.createUIOverlay(groups.get("uiOverlay"));
    }

    private void basicViewLayout() {
        root.getChildren().add(groups.get(GameViewEnum.MAP3D.getStringValue()));
        //root.getChildren().add(groups.get(GameViewEnum.UIOVERLAY.getStringValue()));
        root.getChildren().add(groups.get(GameViewEnum.TIMER.getStringValue()));
        root.getChildren().add(groups.get(GameViewEnum.PLAYER.getStringValue()));
        root.getChildren().add(groups.get(GameViewEnum.TURN.getStringValue()));
        root.getChildren().add(groups.get(GameViewEnum.ROUND.getStringValue()));
        root.getChildren().add(groups.get(GameViewEnum.BUTTON.getStringValue()));
        root.getChildren().add(groups.get(GameViewEnum.AREAINFO.getStringValue()));

        root.getChildren().add(notBasicRoot);
    }

    private void setActive(List<GameViewEnum> views){
        notBasicRoot.getChildren().clear();
        views.forEach(s -> notBasicRoot.getChildren().add(groups.get(s.getStringValue())));
    }


    @Override
    public void update(GameObservable go) {
        setActive(go.getCurrenViews());
    }
}
