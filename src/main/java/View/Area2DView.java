package View;

import Controller.AreaController;
import Enum.AreaColor;
import Observable.AreaObservable;
import Observer.AreaObserver;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;


public class Area2DView implements AreaObserver {

    private Shape shape;
    private AreaController areaCon;
    private AreaColor color;
    private Text text;

    public Area2DView(Node group, AreaController areaCon){
        this.shape = (Shape) ((Group)group).getChildren().get(0);
        this.text = (Text) ((Group)group).getChildren().get(1);
        color = AreaColor.valueOf(shape.getId().split("_")[0]);
        this.areaCon = areaCon;
        areaCon.register(this);
        mouseEvents();
        changeColor();
        CalculatePosText();
    }
    private void CalculatePosText(){
        Bounds boundsInScene = shape.getBoundsInParent();

        if(text.getX() > 0) return;
        text.setX((boundsInScene.getMaxX() - boundsInScene.getMinX()) /2 + boundsInScene.getMinX());
        text.setY((boundsInScene.getMaxY() - boundsInScene.getMinY()) /2 + boundsInScene.getMinY());
    }


    private void mouseEvents() {
        shape.setOnMouseClicked(e -> areaCon.selectActive());
    }

    private void changeColor(){
        shape.setFill(color.getColor());
    }

    @Override
    public void update(AreaObservable ao) {
        shape.setFill((ao.getActive()) ? color.getColor().darker() : color.getColor());
        int numbers = ao.getNumberOfFiches();
        Platform.runLater( () -> text.setText(String.valueOf(numbers)));
    }
}
