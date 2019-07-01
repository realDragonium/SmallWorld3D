package View;

import Managers.AnimationsManager;
import Model.FicheModel;
import Objects.Animatable;
import Objects.AnimationPoint;
import Observer.FicheObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Translate;

import java.io.IOException;

public class Crystal implements Animatable {

    Translate position;
    Group group;
    Node node;

    public Crystal(Group group){
        this.group = group;
        loadCrystal();
        AnimationsManager.getInstance().createUpAndDownAnimation(this, 20, 4);
    }

    public void loadCrystal(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/3dObjects/magical.fxml"));
        try {
            node = fxmlLoader.load();
            node.setScaleX(8);
            node.setScaleY(8);
            node.setScaleZ(8);
            group.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPosition(Translate newPos){
        node.setTranslateX(newPos.getX());
        node.setTranslateY(newPos.getY() - 20);
        node.setTranslateZ(newPos.getZ());

    }

    public void moveToPosition(Translate pos){
        AnimationsManager.getInstance().createMoveToAnimation(this, pos, 3);
    }

    public void movePosition(Translate translate){
        node.setTranslateX(node.getTranslateX() + translate.getX());
        node.setTranslateY(node.getTranslateY() + translate.getY());
        node.setTranslateZ(node.getTranslateZ() + translate.getZ());
    }

    @Override
    public void resetToOrigin(AnimationPoint animPoint) {

    }

    @Override
    public void doAnimation(AnimationPoint animPoint) {
        movePosition(animPoint.getTranslate());

    }

    @Override
    public Translate getCurrentPosition() {
        return new Translate(node.getTranslateX(), node.getLayoutY(), node.getScaleZ());
    }

    @Override
    public double getCurrentXAngle() {
        return 0;
    }

    @Override
    public double getCurrentYAngle() {
        return 0;
    }
}
