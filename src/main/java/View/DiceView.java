package View;

import Controller.DiceController;
import Firebase.FirebaseGameObserver;
import Observable.DiceObservable;
import Observer.DiceObserver;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Enums.GameViewEnum;

public class DiceView implements DiceObserver {

    public ImageView dice;
    public Pane pane;

    private DiceController diceCon;
    private Timeline timeline;

    public DiceView(DiceController diceCon) {
        this.diceCon = diceCon;
    }

    public void initialize(){
        GameViewEnum.DICE.getGroup().getChildren().add(pane);
        diceCon.registreer(this);
        createAnimation();
    }

    private void createAnimation(){
        timeline = new Timeline();
        for(int i = 1; i < 7; i++) {
            int randInt = new Random().nextInt(6);
            if(randInt != 1 && randInt != 2 && randInt != 3) randInt = 0;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * i), new KeyValue(dice.imageProperty(), new Image("/Dice/Dice" + randInt + ".jpg"))));
        }
    }


    private void playAnimation(int waarde) {
        TimerTask hide = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> diceCon.hide());
            }
        };

        timeline.play();
        timeline.setOnFinished(e -> {
            dice.setImage(new Image("/Dice/Dice" + waarde + ".jpg"));
            new Timer().schedule(hide, 500);
        });
    }


    @Override
    public void update(DiceObservable ob) {
        if(ob.isPlaying()){
            playAnimation(ob.getWaarde());
        }
    }

}


