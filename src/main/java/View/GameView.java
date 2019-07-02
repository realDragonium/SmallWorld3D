package View;

import Controller.GameController;
import Observable.GameObservable;
import Observer.GameObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import Enum.GameViewEnum;
import java.util.List;

public class GameView implements GameObserver {

    private Group root;
    private Group notBasicRoot = new Group();
    private GameController gameCon;

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
        gameCon.startGame();

    }

    private void createViews(){
//        gameCon.createMap2DView(GameViewEnum.MAP2D.getGroup());
        gameCon.create3dView();
    }

    private void basicViewLayout() {
        root.getChildren().add(GameViewEnum.MAP3D.getGroup());
        root.getChildren().add(GameViewEnum.UIOVERLAY.getGroup());
        root.getChildren().add(GameViewEnum.SHOP.getGroup());
        root.getChildren().add(GameViewEnum.TIMER.getGroup());
        root.getChildren().add(GameViewEnum.PLAYER.getGroup());
        root.getChildren().add(GameViewEnum.TURN.getGroup());
        root.getChildren().add(GameViewEnum.ROUND.getGroup());
        root.getChildren().add(GameViewEnum.BUTTON.getGroup());
        root.getChildren().add(GameViewEnum.PHASE.getGroup());
        root.getChildren().add(GameViewEnum.COMBINATION.getGroup());
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
