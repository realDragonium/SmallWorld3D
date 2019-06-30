package View;

import Controller.FicheController;
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
        root.getChildren().add(headPane);
        createViews();
        basicViewLayout();
        gameCon.register(this);
        gameCon.setPlayerPositions();
    }

    private void createViews(){
        gameCon.createMap2DView(GameViewEnum.MAP2D.getGroup());
        gameCon.create3dView(GameViewEnum.MAP3D.getGroup());
        gameCon.createPlayerView(GameViewEnum.PLAYER.getGroup(), 0);
        gameCon.createPlayerView(GameViewEnum.PLAYER.getGroup(), 1);
        gameCon.createPlayerView(GameViewEnum.PLAYER.getGroup(), 2);
        gameCon.createPlayerView(GameViewEnum.PLAYER.getGroup(), 3);
        gameCon.createRoundView(GameViewEnum.ROUND.getGroup());
        gameCon.createTurnView(GameViewEnum.TURN.getGroup());
        gameCon.createButtonView(GameViewEnum.BUTTON.getGroup());
        gameCon.createShopView(GameViewEnum.SHOP.getGroup());
        gameCon.createTimerView(GameViewEnum.TIMER.getGroup());
        gameCon.createVervalView(GameViewEnum.VERVAL.getGroup());
        gameCon.createDiceView(GameViewEnum.DICE.getGroup());
        gameCon.createRedeployView(GameViewEnum.REDEPLOY.getGroup());
        gameCon.createInfoView(GameViewEnum.INFO.getGroup());
        gameCon.createAreaInfoView(GameViewEnum.AREAINFO.getGroup());
        gameCon.createUIOverlay(GameViewEnum.UIOVERLAY.getGroup());
        gameCon.createPhaseView(GameViewEnum.PHASE.getGroup());
        gameCon.createNotifiView(GameViewEnum.NOTIFICATION.getGroup());
    }

    private void basicViewLayout() {
        root.getChildren().add(GameViewEnum.MAP3D.getGroup());
//        root.getChildren().add(GameViewEnum.UIOVERLAY.getGroup());
        root.getChildren().add(GameViewEnum.TIMER.getGroup());
        root.getChildren().add(GameViewEnum.PLAYER.getGroup());
        root.getChildren().add(GameViewEnum.TURN.getGroup());
//        root.getChildren().add(GameViewEnum.SHOP.getGroup());
        root.getChildren().add(GameViewEnum.ROUND.getGroup());
        root.getChildren().add(GameViewEnum.BUTTON.getGroup());
        root.getChildren().add(GameViewEnum.AREAINFO.getGroup());
        root.getChildren().add(GameViewEnum.PHASE.getGroup());
        root.getChildren().add(GameViewEnum.NOTIFICATION.getGroup());



        root.getChildren().add(notBasicRoot);
    }

    private void setActive(List<GameViewEnum> views){
        notBasicRoot.getChildren().clear();
        if(views == null) return;
        views.forEach(s -> notBasicRoot.getChildren().add(s.getGroup()));
    }

    @Override
    public void update(GameObservable go) {
        setActive(go.getCurrenViews());
    }
}
