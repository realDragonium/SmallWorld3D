package View;

import Enum.View3DEnum;
import Managers.AnimationsManager;
import Objects.Animatable;
import Objects.AnimationPoint;
import Objects.NormalFXMLLoader;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Translate;

public class Crystal implements Animatable {

    Translate position;
    Group group;
    MeshView crystal;

    public Crystal(){
        group = new Group();
        View3DEnum.CRYSTAL.getGroup().getChildren().add(group);
        loadCrystal();
        AnimationsManager.getInstance().createUpAndDownAnimation(this, 20, 4);
    }

    public void loadCrystal(){
        crystal = new NormalFXMLLoader("/3dObjects/magical.fxml").loadMeshView();
        crystal.setScaleX(8);
        crystal.setScaleY(8);
        crystal.setScaleZ(8);
        group.getChildren().add(crystal);
    }

    public void setPosition(Translate newPos){
        group.setTranslateX(newPos.getX());
        group.setTranslateY(newPos.getY() - 20);
        group.setTranslateZ(newPos.getZ());

    }

    public void moveToPosition(Translate pos){
        AnimationsManager.getInstance().createMoveToAnimation(this, pos, 3);
    }

    public void movePosition(Translate translate){
        group.setTranslateX(group.getTranslateX() + translate.getX());
        group.setTranslateY(group.getTranslateY() + translate.getY());
        group.setTranslateZ(group.getTranslateZ() + translate.getZ());
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
        return new Translate(group.getTranslateX(), group.getLayoutY(), group.getScaleZ());
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
