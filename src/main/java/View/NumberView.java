package View;

import Controller.NumberController;
import Objects.Xform;
import Observable.NumberObservable;
import Observer.NumberObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;

import java.io.IOException;

public class NumberView implements NumberObserver {

    private NumberController numberCon;
    Group group;
    Xform xForm = new Xform();
    int currentNumber;

    public NumberView(NumberController numberCon, Group group){
        this.numberCon = numberCon;
        xForm.setScaleX(10);
        xForm.setScaleY(10);
        xForm.setScaleZ(10);
        loadNumber(1);
        group.getChildren().add(xForm);
        numberCon.registerObserver(this);
    }

    public void loadNumber(int currentNumber){
        try {
            MeshView meshView = FXMLLoader.load(getClass().getResource("/3dObjects/numbers/" + currentNumber + ".fxml"));
            xForm.getChildren().clear();
            xForm.getChildren().add(meshView);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(NumberObservable no) {
      //  xForm.relocate(no.getPosition().getX(), no.getPosition().getY());
        xForm.setRotate(0, no.getYAngle(), 0);
        if(currentNumber != no.getCurNumber()) {
            loadNumber(no.getCurNumber());
            currentNumber = no.getCurNumber();
        }

    }
}
