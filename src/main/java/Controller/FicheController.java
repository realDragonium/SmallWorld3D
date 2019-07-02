package Controller;

import Managers.AnimationsManager;
import Model.FicheModel;
import Objects.Animatable;
import Objects.AnimationPoint;
import Observer.FicheObserver;
import View.Fiche3DView;
import javafx.scene.transform.Translate;

public class FicheController implements Animatable {

    FicheModel model;
    CombinationController combiCon;

    public FicheController(int defenceValue, CombinationController combi){
        combiCon = combi;
        model = new FicheModel(defenceValue, combiCon.getRace());
        new Fiche3DView(this);
    }

    public void registerObserver(FicheObserver fo){
        model.register(fo);
    }

    public void setPosition(Translate newPos){
        model.setPosition(newPos);
    }

    public void moveToPosition(Translate pos){
        AnimationsManager.getInstance().createMoveToAnimationArch(this, pos, 3);
    }

    @Override
    public void resetToOrigin(AnimationPoint animPoint) {

    }

    @Override
    public void doAnimation(AnimationPoint animPoint) {
        model.moveFiche(animPoint.getTranslate());

    }

    @Override
    public Translate getCurrentPosition() {
        return model.getPosition();
    }

    @Override
    public double getCurrentXAngle() {
        return 0;
    }

    @Override
    public double getCurrentYAngle() {
        return 0;
    }

    public int getDefenceValue() {
        return model.getDefenceValue();
    }

    public CombinationController getCombiCon() {
        return combiCon;
    }
}
