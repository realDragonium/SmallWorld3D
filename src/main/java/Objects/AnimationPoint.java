package Objects;

import javafx.scene.transform.Translate;

public class AnimationPoint {
    Translate translate;
    double xAngle;
    double yAngle;

    public AnimationPoint(){
        translate = new Translate(0,0,0);
        xAngle = 0;
        yAngle = 0;
    }

    public AnimationPoint(Translate translate){
        this.translate = translate;
        xAngle = 0;
        yAngle = 0;
    }

    public AnimationPoint(double xAngle, double yAngle){
        this.xAngle = xAngle;
        this.yAngle = yAngle;
        translate = new Translate(0,0,0);
    }

    public AnimationPoint(Translate translate, double xAngle, double yAngle){
        this.translate = translate;
        this.xAngle = xAngle;
        this.yAngle = yAngle;
    }

    public Translate getTranslate(){
        return translate;
    }

    public double getXAngle(){
        return xAngle;
    }

    public double getYAngle(){
        return yAngle;
    }
}

