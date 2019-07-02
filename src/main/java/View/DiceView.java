package View;

import Controller.DiceController;
import Observable.DiceObservable;
import Observer.DiceObserver;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import Enums.GameViewEnum;

public class DiceView implements DiceObserver {

    private Group root;
    private DiceController diceController;
    private ArrayList<Image> list = new ArrayList<>();
    private int counter = 0;

    private ImageView showImageView = new ImageView(getClass().getResource("/Dice/DiceNul.jpg").toExternalForm());
    private Image One = new Image("/Dice/DiceNul.jpg");
    private Image Two = new Image("/Dice/DiceNul.jpg");
    private Image Three = new Image("/Dice/DiceNul.jpg");
    private Image Four = new Image("/Dice/DiceOne.jpg");
    private Image Five = new Image("/Dice/DiceTwo.jpg");
    private Image Six = new Image("/Dice/DiceThree.jpg");

    private Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(500), new KeyValue(showImageView.imageProperty(), One)),
            new KeyFrame(Duration.millis(1000), new KeyValue(showImageView.imageProperty(), Four)),
            new KeyFrame(Duration.millis(1500), new KeyValue(showImageView.imageProperty(), Three)),
            new KeyFrame(Duration.millis(2000), new KeyValue(showImageView.imageProperty(), Six)),
            new KeyFrame(Duration.millis(2500), new KeyValue(showImageView.imageProperty(), Two)),
            new KeyFrame(Duration.millis(3000), new KeyValue(showImageView.imageProperty(), Five))
    );

    public DiceView(DiceController diceCon) {
        root = GameViewEnum.DICE.getGroup();
        diceController = diceCon;
    }

    private void createScene() {
        list.add(new Image("/Dice/DiceNul.jpg"));
        list.add(new Image("/Dice/DiceNul.jpg"));
        list.add(new Image("/Dice/DiceNul.jpg"));
        list.add(new Image("/Dice/DiceOne.jpg"));
        list.add(new Image("/Dice/DiceTwo.jpg"));
        list.add(new Image("/Dice/DiceThree.jpg"));

        GridPane gridPane = new GridPane();
        gridPane.add(showImageView, 0, 1);


        root.getChildren().add(gridPane);
        gridPane.setTranslateX(550);
        gridPane.setTranslateY(720);
    }

    private void playAnimation(int waarde) {

        timeline.play();
        timeline.setOnFinished(e -> showImageView.setImage(list.get(waarde)));


    }


    @Override
    public void update(DiceObservable ob) {
        if(ob.isPlaying()){
            playAnimation(ob.getWaarde());
        }
    }
}


